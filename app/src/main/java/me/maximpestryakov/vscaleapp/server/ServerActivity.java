package me.maximpestryakov.vscaleapp.server;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.Menu;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import me.maximpestryakov.vscaleapp.App;
import me.maximpestryakov.vscaleapp.R;
import me.maximpestryakov.vscaleapp.api.model.Server;

import static android.support.design.widget.Snackbar.LENGTH_SHORT;

public class ServerActivity extends AppCompatActivity implements ServerView {

    @BindView(R.id.serverUpdate)
    SwipeRefreshLayout serverUpdate;

    @BindView(R.id.serverOsLogo)
    AppCompatImageView serverOsLogo;

    @BindView(R.id.serverName)
    TextView serverName;

    @BindView(R.id.serverInfo)
    TextView serverInfo;

    private int ctid;
    private ServerPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        ButterKnife.bind(this);

        ctid = getIntent().getIntExtra("ctid", 0);
        showServerInfo();

        presenter = new ServerPresenter(this);
        presenter.loadServerInfo(ctid);

        serverUpdate.setOnRefreshListener(() -> presenter.loadServerInfo(ctid));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.clear();

        Realm realm = Realm.getDefaultInstance();
        Server server = realm.where(Server.class).equalTo("ctid", ctid).findAll().first();
        switch (server.getStatus()) {
            case STARTED:
                menu.add("Выключить").setOnMenuItemClickListener(item -> {
                    presenter.stopServer(server.getCtid());
                    return true;
                });
                menu.add("Перзагрузить").setOnMenuItemClickListener(item -> {
                    presenter.restartServer(server.getCtid());
                    return true;
                });
                break;
            case STOPPED:
                menu.add("Запустить").setOnMenuItemClickListener(item -> {
                    presenter.startServer(server.getCtid());
                    return true;
                });
                break;
        }
        menu.add("Переустановить сервер");
        menu.add("Создать бэкап");
        menu.add("Удалить");
        return true;
    }

    @Override
    public void showServerInfo() {
        Realm realm = Realm.getDefaultInstance();
        Server server = realm.where(Server.class).equalTo("ctid", ctid).findAll().first();
        supportInvalidateOptionsMenu();
        serverUpdate.setRefreshing(false);
        setTitle(server.getName());
        serverName.setText(server.getMadeFrom());
        switch (server.getOs()) {
            case CENTOS:
                serverOsLogo.setImageResource(R.drawable.ic_centos);
                break;
            case DEBIAN:
                serverOsLogo.setImageResource(R.drawable.ic_debian);
                break;
            case FEDORA:
                serverOsLogo.setImageResource(R.drawable.ic_fedora);
                break;
            case OPENSUSE:
                serverOsLogo.setImageResource(R.drawable.ic_opensuse);
                break;
            case UBUNTU:
                serverOsLogo.setImageResource(R.drawable.ic_ubuntu);
                break;
        }
        switch (server.getPlan()) {
            case SMALL:
                serverInfo.setText(App.string(R.string.rplan_info_small));
                break;
            case MEDIUM:
                serverInfo.setText(App.string(R.string.rplan_info_medium));
                break;
            case LARGE:
                serverInfo.setText(App.string(R.string.rplan_info_large));
                break;
            case HUGE:
                serverInfo.setText(App.string(R.string.rplan_info_huge));
                break;
            case MONSTER:
                serverInfo.setText(App.string(R.string.rplan_info_monster));
                break;
        }
    }

    @Override
    public void showLoading() {
        serverUpdate.setRefreshing(true);
    }

    @Override
    public void showInternetConnectionProblem() {

        serverUpdate.setRefreshing(false);

        Snackbar.make(serverUpdate, R.string.no_internet, LENGTH_SHORT)
                .setAction(R.string.retry, v -> presenter.loadServerInfo(ctid))
                .show();
    }
}
