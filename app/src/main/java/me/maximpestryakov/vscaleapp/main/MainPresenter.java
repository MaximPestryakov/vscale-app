package me.maximpestryakov.vscaleapp.main;

import io.realm.Realm;
import me.maximpestryakov.vscaleapp.App;
import me.maximpestryakov.vscaleapp.R;
import me.maximpestryakov.vscaleapp.util.MyCallback;

class MainPresenter {

    private MainView view;

    MainPresenter(MainView view) {
        this.view = view;
    }

    void loadServerList() {
        view.showLoading();

        if (App.getSharedPreferences().getString(
                App.string(R.string.token_preference_key), "").isEmpty()) {
            view.showTokenWarning(
                    App.string(R.string.no_token),
                    App.string(R.string.need_token));
            return;
        }

        App.getApi().getServers().enqueue(new MyCallback<>(
                (call, response) -> {
                    if (response.isSuccessful()) {
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(response.body());
                        realm.commitTransaction();
                        view.showServerList();

                    } else if (response.code() == 401) {
                        view.showTokenWarning(
                                App.string(R.string.no_token),
                                App.string(R.string.need_token));
                    } else if (response.code() == 403) {
                        view.showTokenWarning(
                                App.string(R.string.wrong_token),
                                App.string(R.string.need_right_token));
                    }
                },
                (call, t) -> view.showInternetConnectionProblem()));
    }
}
