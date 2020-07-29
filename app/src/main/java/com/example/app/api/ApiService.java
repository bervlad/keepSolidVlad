package com.example.app.api;

import com.example.app.model.VolumeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/books/v1/volumes")
    Call<VolumeResponse> getVolumes(@Query("q") String query);

}
