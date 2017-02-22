package me.maximpestryakov.vscaleapp.api;

import me.maximpestryakov.vscaleapp.api.model.Account;
import retrofit2.Call;
import retrofit2.http.GET;

public interface VscaleApi {
    @GET("account")
    Call<Account> getAccount();
}
