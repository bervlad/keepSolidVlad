package com.example.app.fragment.screens.chooser;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.app.base.BasePresenter;
import com.example.app.base.BaseView;
import com.example.app.model.VolumeModelItem;
import com.example.app.utils.listeners.ObjectSelectListener;

import java.util.List;

public interface ChooserContract {
    interface View extends BaseView <Presenter> {
        void showInputError();
        void showRequestError(@NonNull String message);
        void makeErrorToast (@NonNull String message);
        void observeItems(LiveData<List<VolumeModelItem>> itemsLiveData);
        void stopObserving(LiveData<List<VolumeModelItem>> liveRepoData);

    }

    interface Presenter extends BasePresenter<View> {

       void searchVolumes(@NonNull String query, boolean isInitial);

    }
}
