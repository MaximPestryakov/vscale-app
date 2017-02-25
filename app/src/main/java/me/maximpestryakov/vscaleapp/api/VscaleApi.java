package me.maximpestryakov.vscaleapp.api;

import java.util.List;

import me.maximpestryakov.vscaleapp.api.model.Account;
import me.maximpestryakov.vscaleapp.api.model.Server;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface VscaleApi {
    @GET("account")
    Call<Account> getAccount();

    @GET("scalets")
    Call<List<Server>> getServers();

    @GET("scalets/{ctid}")
    Call<Server> getServer(@Path("ctid") int ctid);

    @PATCH("scalets/{ctid}/start")
    Call<Server> startServer(@Path("ctid") int ctid);

    @PATCH("scalets/{ctid}/stop")
    Call<Server> stopServer(@Path("ctid") int ctid);

    @PATCH("scalets/{ctid}/restart")
    Call<Server> restartServer(@Path("ctid") int ctid);


}
