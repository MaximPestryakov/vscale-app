package me.maximpestryakov.vscaleapp.main;

import java.util.List;

import me.maximpestryakov.vscaleapp.App;
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
        if (App.getSharedPreferences().getString("token_preference", "").isEmpty()) {
            view.showTokenWarning("Токен не установлен",
                    "Для работы приложения необходимо установить токен");
            return;
        }

        App.getApi().getServers().enqueue(new Callback<List<Server>>() {
            @Override
            public void onResponse(Call<List<Server>> call, Response<List<Server>> response) {
                if (response.code() == 401) {
                    view.showTokenWarning("Токен не установлен",
                            "Для работы приложения необходимо установить токен");
                } else if (response.code() == 403) {
                    view.showTokenWarning("Неверный токен",
                            "Для работы приложения необходимо установить верный токен");
                } else if (response.code() == 200) {
                    view.showServerList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Server>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        /*
        App.getApi().getAccount().enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Log.d("CODE", response.code() + "");
                Account account = response.body();
                if (account != null) {
                    Log.d("NAME:", response.body().getName());
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                t.printStackTrace();
            }
        });
        */
    }
}
