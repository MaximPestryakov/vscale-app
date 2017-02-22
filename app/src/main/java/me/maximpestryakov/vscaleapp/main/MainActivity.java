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

    private MainPresenter presenter;

    @BindView(R.id.serverList)
    RecyclerView serverList;

    @BindView(R.id.addServer)
    FloatingActionButton addServer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter(this);

        presenter.loadServerList();
    }

    @Override
    public void showServerList(List<Server> servers) {
        Log.d("showServerList", "here");
        serverList.setHasFixedSize(true);
        serverList.setLayoutManager(new LinearLayoutManager(this));
        serverList.setAdapter(new ServerListAdapter(this, servers));
    }

    @Override
    public void showTokenWarning(String titile, String message) {
        new AlertDialog.Builder(this)
                .setTitle(titile)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Перейти в настройки", (dialog, which) -> {
                    Intent intent = new Intent(this, SettingsActivity.class);
                    startActivity(intent);

                })
                .setNegativeButton("Выход", (dialog, which) -> finish())
                .create()
                .show();
    }
}
