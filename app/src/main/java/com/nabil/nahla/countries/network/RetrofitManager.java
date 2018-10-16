package com.nabil.nahla.countries.network;


import android.app.ProgressDialog;
import android.content.Context;

import com.nabil.nahla.countries.R;
import com.nabil.nahla.countries.listeners.OnGetDataListener;
import com.nabil.nahla.countries.models.Country;
import com.nabil.nahla.countries.utils.CheckConnection;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.nabil.nahla.countries.network.Endpoints.BASE_URL;

public class RetrofitManager {

    private static RetrofitManager retrofitManager;
    private RetrofitService api;

    private RetrofitManager() {
        createApi();
    }

    public static RetrofitManager getInstance() {
        retrofitManager = new RetrofitManager();
        return retrofitManager;
    }

    void createApi() {
        final OkHttpClient.Builder clientBuilder;
        final OkHttpClient client;

        clientBuilder = new OkHttpClient.Builder()
                .readTimeout(1000, TimeUnit.SECONDS)
                .connectTimeout(1000, TimeUnit.SECONDS);

        client = clientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(RetrofitService.class);
    }

    public void getAllCountries(final Context context, final OnGetDataListener<ArrayList<Country>, String> listener, final ProgressDialog dialog) {

        if (!CheckConnection.setCheckConnection(context)) {
            listener.onFailed(context.getResources().getString(R.string.error_connection_internet));
            return;
        }

        showLoadingDialog(dialog, context);

        api.getAllCountries().enqueue(new Callback<ArrayList<Country>>() {
            @Override
            public void onResponse(Call<ArrayList<Country>> call, Response<ArrayList<Country>> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onFailed(response.message());
                }

                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ArrayList<Country>> call, Throwable t) {
                dialog.dismiss();
                listener.onFailed(t.toString());
            }
        });
    }

    private void showLoadingDialog(ProgressDialog dialog, final Context context) {
        String pleaseWait = context.getString(R.string.dialog_please_wait);
        dialog.setMessage(pleaseWait);
        dialog.setCancelable(false);
        dialog.show();
    }

}
