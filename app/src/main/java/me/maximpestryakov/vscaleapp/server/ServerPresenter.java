package me.maximpestryakov.vscaleapp.server;

import io.realm.Realm;
import me.maximpestryakov.vscaleapp.App;
import me.maximpestryakov.vscaleapp.api.model.Server;
import me.maximpestryakov.vscaleapp.util.MyCallback;

class ServerPresenter {

    private ServerView view;
    private App app;

    ServerPresenter(ServerView view, App app) {
        this.view = view;
        this.app = app;
    }

    void loadServerInfo(int ctid) {
        view.showLoading();
        app.getApi().getServer(ctid).enqueue(new MyCallback<>(
                (call, response) -> {
                    if (response.isSuccessful()) {
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(response.body());
                        realm.commitTransaction();
                        view.showServerInfo();
                    }
                },
                (call, t) -> view.showInternetConnectionProblem()
        ));
    }

    void startServer(int ctid) {
        app.getApi().startServer(ctid).enqueue(new MyCallback<>(
                (call, response) -> {
                    if (response.isSuccessful()) {
                        Server server = response.body();
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(server);
                        realm.commitTransaction();
                        view.showServerInfo();
                        loadServerInfo(server.getCtid());
                        return;
                    }
                    loadServerInfo(ctid);
                },
                (call, t) -> view.showInternetConnectionProblem()
        ));
    }

    void restartServer(int ctid) {
        app.getApi().restartServer(ctid).enqueue(new MyCallback<>(
                (call, response) -> {
                    if (response.isSuccessful()) {
                        Server server = response.body();
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(server);
                        realm.commitTransaction();
                        view.showServerInfo();
                        loadServerInfo(server.getCtid());
                        return;
                    }
                    loadServerInfo(ctid);
                },
                (call, t) -> view.showInternetConnectionProblem()
        ));
    }

    void stopServer(int ctid) {
        app.getApi().stopServer(ctid).enqueue(new MyCallback<>(
                (call, response) -> {
                    if (response.isSuccessful()) {
                        Server server = response.body();
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(server);
                        realm.commitTransaction();
                        view.showServerInfo();
                        loadServerInfo(server.getCtid());
                        return;
                    }
                    loadServerInfo(ctid);
                },
                (call, t) -> view.showInternetConnectionProblem()
        ));
    }
}
