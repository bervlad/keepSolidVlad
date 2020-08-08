package com.example.app.fragment.screens.viewer;

import com.example.app.base.BasePresenter;
import com.example.app.base.BaseView;
import com.example.app.fragment.screens.chooser.ChooserContract;

public interface ViewerContract {

    interface View extends BaseView<ViewerContract.Presenter> {


    }

    interface Presenter extends BasePresenter<ViewerContract.View> {

    }

}
