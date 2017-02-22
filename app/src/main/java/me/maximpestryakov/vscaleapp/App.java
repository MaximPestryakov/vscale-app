package me.maximpestryakov.vscaleapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import me.maximpestryakov.vscaleapp.api.VscaleApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class App extends Application {

    private final static String VSCALE_URl = "https://api.vscale.io/v1/";

    private static VscaleApi api;
    private static SharedPreferences sharedPref;

    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        sharedPref = getSharedPreferences(getString(R.string.preference_file_key),
                Context.MODE_PRIVATE);

        retrofit = new Retrofit.Builder()
                .baseUrl(VSCALE_URl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(chain -> {
                            String token = sharedPref.getString("token", "");
                            return chain.proceed(chain.request().newBuilder()
                                    .header("X-Token", token)
                                    .build());
                        })
                        .build())
                .build();

        api = retrofit.create(VscaleApi.class);
    }

    public static VscaleApi getApi() {
        return api;
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPref;
    }
}
