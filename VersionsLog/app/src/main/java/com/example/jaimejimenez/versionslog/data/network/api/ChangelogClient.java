package com.example.jaimejimenez.versionslog.data.network.api;

import android.util.Log;

import com.example.jaimejimenez.versionslog.data.db.models.ChangelogCambio;
import com.example.jaimejimenez.versionslog.data.db.models.ChangelogVersion;
import com.example.jaimejimenez.versionslog.data.repositories.ChangelogChangesRepository;
import com.example.jaimejimenez.versionslog.data.repositories.ChangelogVersionRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jaimejimenez on 19/03/18.
 */

public class ChangelogClient {
    private static ChangelogClient mInstance;
    private Retrofit mRetrofit;


    private ChangelogClient() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.105.11:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static ChangelogClient getInstance() {
        if (mInstance == null)
            mInstance = new ChangelogClient();

        return mInstance;
    }


    public void getVersionsList() {
        ChangelogService apiService = mRetrofit.create(ChangelogService.class);
        Call<List<ChangelogVersion>> apiCall = apiService.getVersions();

        apiCall.enqueue(new Callback<List<ChangelogVersion>>() {
            @Override
            public void onResponse(Call<List<ChangelogVersion>> call, Response<List<ChangelogVersion>> response) {
                ChangelogVersionRepository.getInstance().updateVersionsTable(response.body());
            }

            @Override
            public void onFailure(Call<List<ChangelogVersion>> call, Throwable t) {
                Log.e("ChanglogClient", t.getMessage());
            }
        });
    }


    public void getChangesList() {
        ChangelogService apiService = mRetrofit.create(ChangelogService.class);
        Call<List<ChangelogCambio>> apiCall = apiService.getChanges();

        apiCall.enqueue(new Callback<List<ChangelogCambio>>() {
            @Override
            public void onResponse(Call<List<ChangelogCambio>> call, Response<List<ChangelogCambio>> response) {
                ChangelogChangesRepository.getInstance().updateChangesTable(response.body());
            }

            @Override
            public void onFailure(Call<List<ChangelogCambio>> call, Throwable t) {
                Log.e("ChanglogClient", t.getMessage());
            }
        });
    }
}
