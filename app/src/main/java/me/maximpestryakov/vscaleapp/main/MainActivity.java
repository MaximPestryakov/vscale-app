package me.maximpestryakov.vscaleapp.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.maximpestryakov.vscaleapp.R;
import me.maximpestryakov.vscaleapp.api.model.Server;
import me.maximpestryakov.vscaleapp.api.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.serverList)
    RecyclerView serverList;

    @BindView(R.id.addServer)
    FloatingActionButton addServer;

    private AlertDialog warningDialog;
    private MainPresenter presenter;
    private MainDataFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter(this);

        if (savedInstanceState == null) {
            fragment = new MainDataFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mainFragmentContainer, fragment)
                    .commit();
        } else {
            fragment = (MainDataFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.mainFragmentContainer);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (fragment.getState()) {
            case State.SHOW_TOKEN_WARNING:
                showTokenWarning(fragment.getDialogTitle(), fragment.getDialogMessage());
                break;
            case State.SHOW_SERVER_LIST:
                showServerList(fragment.getServers());
                break;
            default:
                presenter.loadServerList();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (warningDialog != null) {
            warningDialog.dismiss();
        }
    }

    @Override
    public void showServerList(List<Server> servers) {
        fragment.setState(State.SHOW_SERVER_LIST);
        fragment.setServers(servers);

        serverList.setHasFixedSize(true);
        serverList.setLayoutManager(new LinearLayoutManager(this));
        serverList.setAdapter(new ServerListAdapter(this, servers));
    }

    @Override
    public void showTokenWarning(String title, String message) {
        fragment.setState(State.SHOW_TOKEN_WARNING);
        fragment.setDialogTitle(title);
        fragment.setDialogMessage(message);

        warningDialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Перейти в настройки", (dialog, which) -> {
                    Intent intent = new Intent(this, SettingsActivity.class);
                    fragment.setState(-1);
                    startActivity(intent);

                })
                .setNegativeButton("Выход", (dialog, which) -> finish())
                .create();
        warningDialog.show();
    }
}
