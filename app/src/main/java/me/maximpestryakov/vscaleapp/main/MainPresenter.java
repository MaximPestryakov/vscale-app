package me.maximpestryakov.vscaleapp.main;

import java.util.List;

import me.maximpestryakov.vscaleapp.App;
import me.maximpestryakov.vscaleapp.R;
import me.maximpestryakov.vscaleapp.api.model.Server;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

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

        App.getApi().getServers().enqueue(new Callback<List<Server>>() {
            @Override
            public void onResponse(Call<List<Server>> call, Response<List<Server>> response) {
                if (response.code() == 401) {
                    view.showTokenWarning(
                            App.string(R.string.no_token),
                            App.string(R.string.need_token));
                } else if (response.code() == 403) {
                    view.showTokenWarning(
                            App.string(R.string.wrong_token),
                            App.string(R.string.need_right_token));
                } else if (response.code() == 200) {
                    view.showServerList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Server>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
