package me.maximpestryakov.vscaleapp.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import me.maximpestryakov.vscaleapp.R;
import me.maximpestryakov.vscaleapp.api.model.Server;
import me.maximpestryakov.vscaleapp.settings.SettingsActivity;

import static android.support.design.widget.Snackbar.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.mainLayout)
    CoordinatorLayout mainLayout;

    @BindView(R.id.serverList)
    RecyclerView serverList;

    @BindView(R.id.addServer)
    FloatingActionButton addServer;

    @BindView(R.id.refreshServerList)
    SwipeRefreshLayout refreshServerList;

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

        refreshServerList.setOnRefreshListener(presenter::loadServerList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showServerList();
        switch (fragment.getState()) {
            case State.SHOW_TOKEN_WARNING:
                showTokenWarning(fragment.getDialogTitle(), fragment.getDialogMessage());
                break;
            case State.SHOW_SERVER_LIST:
                break;
            case State.SHOW_INTERNET_CONNECTION_PROBLEM:
                showInternetConnectionProblem();
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
    public void showServerList() {
        fragment.setState(State.SHOW_SERVER_LIST);

        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Server> query = realm.where(Server.class);
        RealmResults<Server> results = query.findAll();
        List<Server> servers = new ArrayList<>(results);

        serverList.setHasFixedSize(true);
        serverList.setLayoutManager(new LinearLayoutManager(this));
        serverList.setAdapter(new ServerListAdapter(this, servers));

        refreshServerList.setRefreshing(false);
    }

    @Override
    public void showTokenWarning(String title, String message) {
        fragment.setState(State.SHOW_TOKEN_WARNING);
        fragment.setDialogTitle(title);
        fragment.setDialogMessage(message);

        refreshServerList.setRefreshing(false);

        warningDialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.go_to_settings), (dialog, which) -> {
                    Intent intent = new Intent(this, SettingsActivity.class);
                    fragment.setState(-1);
                    startActivity(intent);

                })
                .setNegativeButton(getString(R.string.exit), (dialog, which) -> finish())
                .create();
        warningDialog.show();
    }

    @Override
    public void showInternetConnectionProblem() {
        fragment.setState(State.SHOW_INTERNET_CONNECTION_PROBLEM);

        refreshServerList.setRefreshing(false);

        Snackbar.make(mainLayout, R.string.no_internet, LENGTH_SHORT)
                .setAction(R.string.retry, v -> presenter.loadServerList())
                .show();
    }

    @Override
    public void showLoading() {
        fragment.setState(State.SHOW_LOADING);

        refreshServerList.setRefreshing(true);
    }
}
