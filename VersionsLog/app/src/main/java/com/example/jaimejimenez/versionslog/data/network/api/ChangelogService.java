package com.example.jaimejimenez.versionslog.data.network.api;

import com.example.jaimejimenez.versionslog.data.db.models.ChangelogCambio;
import com.example.jaimejimenez.versionslog.data.db.models.ChangelogVersion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jaimejimenez on 19/03/18.
 */

public interface ChangelogService {

    @GET("/mavenproject1/api/log/versions")
    Call<List<ChangelogVersion>> getVersions();

    @GET("/mavenproject1/api/log/changes")
    Call<List<ChangelogCambio>> getChanges();
}
