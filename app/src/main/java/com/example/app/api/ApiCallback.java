package com.example.app.api;

import com.example.app.model.VolumeErrorItem;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public abstract class ApiCallback<T> implements Callback<T> {
    public abstract void success(Response<T> response);

    public abstract void failure(VolumeErrorItem volumeErrorItem);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (!response.isSuccessful()) {
            Converter<ResponseBody, VolumeErrorItem> converter = RestClient.getInstance().getRetrofit().responseBodyConverter(VolumeErrorItem.class, new Annotation[0]);

            try {
                VolumeErrorItem repoError = converter.convert(response.errorBody());
                failure(repoError);
            } catch (Exception e) {
                failure(new VolumeErrorItem("Unhandled error! Code: " + response.code()));
            }
        } else {
            success(response);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        failure(new VolumeErrorItem("Unexpected error! Info: " + t.getMessage()));
    }
}
