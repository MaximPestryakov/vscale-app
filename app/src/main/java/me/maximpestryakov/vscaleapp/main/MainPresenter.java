package me.maximpestryakov.vscaleapp.main;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import me.maximpestryakov.vscaleapp.App;
import me.maximpestryakov.vscaleapp.R;
import me.maximpestryakov.vscaleapp.api.model.Server;
import me.maximpestryakov.vscaleapp.api.model.Token;
import me.maximpestryakov.vscaleapp.util.MyCallback;

class MainPresenter {

    private MainView view;
    private App app;

    MainPresenter(MainView view, App app) {
        this.view = view;
        this.app = app;
    }

    void loadServerList() {
        view.showLoading();

        /*
        if (app.getSharedPreferences().getString(app.getString(R.string.token_preference_key), "").isEmpty()) {
            view.showTokenWarning(R.string.no_token, R.string.need_token);
            return;
        }
        */

        Realm realm = Realm.getDefaultInstance();
        ArrayList<Token> tokens = new ArrayList(realm.where(Token.class).findAll());
        realm.close();
        if (tokens.isEmpty()) {
            view.showTokenWarning(R.string.no_token, R.string.need_token);
            return;
        }

        List<Server> servers = new ArrayList<>();

        for (Token token : tokens) {
            app.getApi().getServers(token.getValue()).enqueue(new MyCallback<>(
                    (call, response) -> {
                        if (response.isSuccessful()) {
                            servers.addAll(response.body());
                        } else if (response.code() == 401) {
                            view.showTokenWarning(R.string.no_token, R.string.need_token);
                        } else if (response.code() == 403) {
                            view.showTokenWarning(R.string.wrong_token, R.string.need_right_token);
                        }
                    },
                    (call, t) -> view.showInternetConnectionProblem()));
        }

        realm.beginTransaction();
        realm.delete(Server.class);
        realm.copyToRealm(servers);
        realm.commitTransaction();

        view.showServerList();
    }
}
