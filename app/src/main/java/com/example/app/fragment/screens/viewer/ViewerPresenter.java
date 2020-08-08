package com.example.app.fragment.screens.viewer;

import com.example.app.fragment.screens.chooser.ChooserContract;

public class ViewerPresenter implements ViewerContract.Presenter{

    private ViewerContract.View view;

    public ViewerPresenter () {

    }

    @Override
    public void takeView(ViewerContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }
}
