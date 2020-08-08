package com.example.app.base;

import com.example.app.fragment.screens.chooser.ChooserContract;

public interface BaseView<T> {

    void setPresenter(T presenter);

    void showProgress();

    void hideProgress();

    void hideKeyboard();

}
