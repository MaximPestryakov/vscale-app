package me.maximpestryakov.vscaleapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import io.realm.Realm;
import me.maximpestryakov.vscaleapp.api.VscaleApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class App extends Application {

    private static final String VSCALE_URl = "https://api.vscale.io/v1/";

    private VscaleApi api;
    private SharedPreferences sharedPref;

    public static App from(Context context) {
        return (App) context.getApplicationContext();
    }

    public VscaleApi getApi() {
        return api;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPref;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(VSCALE_URl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(chain -> {
                            String token = sharedPref.getString(
                                    getString(R.string.token_preference_key), "");
                            return chain.proceed(chain.request().newBuilder()
                                    .header("X-Token", token)
                                    .build());
                        })
                        .build())
                .build();

        api = retrofit.create(VscaleApi.class);
    }
}
