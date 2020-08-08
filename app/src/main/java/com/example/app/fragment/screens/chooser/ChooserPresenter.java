package com.example.app.fragment.screens.chooser;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.app.api.ApiCallback;
import com.example.app.api.RestClient;
import com.example.app.database.AppDatabase;
import com.example.app.database.AppDatabase_Impl;
import com.example.app.model.VolumeErrorItem;
import com.example.app.model.VolumeModelItem;
import com.example.app.model.VolumeResponse;
import com.example.app.utils.listeners.ApplicationManager;

import java.util.List;

import retrofit2.Response;

public class ChooserPresenter implements ChooserContract.Presenter {

    private ChooserContract.View view;
    private ApplicationManager applicationManager;
    private AppDatabase appDatabase;
    private LiveData<List<VolumeModelItem>> liveVolumeData;

    public ChooserPresenter(ApplicationManager applicationManager, AppDatabase database) {
        this.applicationManager=applicationManager;
        this.appDatabase=database;
    }

    @Override
    public void searchVolumes(@NonNull String query) {

        if (query.trim().isEmpty()) {
            view.showInputError();
            return;
        }

        view.hideKeyboard();
        view.showProgress();

        OnRequestFinishedListener onRequestFinishedListener = new OnRequestFinishedListener() {
            @Override
            public void onRequestFinished(@Nullable VolumeErrorItem errorItem) {
                view.hideProgress();
                if (errorItem != null) {
                    view.showRequestError(errorItem.getMessage() + "; " + errorItem.getDocumentation_url());
                }
            }
        };

            loadVolumes(query, onRequestFinishedListener);
    }

    @Override
    public void takeView(ChooserContract.View view) {
        this.view = view;
        liveVolumeData = appDatabase.repoItemDao().getAll();
        view.observeItems(liveVolumeData);

    }

    @Override
    public void dropView() {
        view.stopObserving(liveVolumeData);
        this.view = null;
        liveVolumeData = null;
    }

    interface OnRequestFinishedListener {

        void onRequestFinished(@Nullable VolumeErrorItem errorItem);

    }

    private void loadVolumes(String bookName, OnRequestFinishedListener onRequestFinishedListener) {
       // showProgressBlock();
        RestClient.getInstance().getService().getVolumes("intitle:"+ bookName).enqueue(new ApiCallback<VolumeResponse>() {

            @Override
            public void success(Response<VolumeResponse> response) {

                if (response.body().getItems()!=null) {
                    updateList(response.body().getItems());

                } else view.makeErrorToast("No books found");
               // hideProgressBlock();
            }

            @Override
            public void failure(VolumeErrorItem volumeError) {
//                handleError(volumeError);
//                hideProgressBlock();
                if (onRequestFinishedListener != null) {
                    onRequestFinishedListener.onRequestFinished(volumeError);
                }
            }


        });
    }

    private void updateList(List<VolumeModelItem> itemsToUpdate) {
        appDatabase.repoItemDao().deleteAll();
        appDatabase.repoItemDao().insert(itemsToUpdate);
    }


}
