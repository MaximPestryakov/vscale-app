package me.maximpestryakov.vscaleapp.util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCallback<T> implements Callback<T> {

    private MyResponse<T> myResponse;
    private MyFailure<T> myFailure;

    public MyCallback(MyResponse<T> myResponse, MyFailure<T> myFailure) {
        this.myResponse = myResponse;
        this.myFailure = myFailure;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        myResponse.onResponse(call, response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        myFailure.onFailure(call, t);
    }

    @FunctionalInterface
    public interface MyResponse<T> {
        void onResponse(Call<T> call, Response<T> response);
    }

    @FunctionalInterface
    public interface MyFailure<T> {
        void onFailure(Call<T> call, Throwable t);
    }
}
