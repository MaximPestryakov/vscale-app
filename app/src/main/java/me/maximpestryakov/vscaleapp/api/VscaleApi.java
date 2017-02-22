package me.maximpestryakov.vscaleapp.api;

import java.util.List;

import me.maximpestryakov.vscaleapp.api.model.Account;
import me.maximpestryakov.vscaleapp.api.model.Server;
import retrofit2.Call;
import retrofit2.http.GET;

public interface VscaleApi {
    @GET("account")
    Call<Account> getAccount();

    @GET("scalets")
    Call<List<Server>> getServers();
}
