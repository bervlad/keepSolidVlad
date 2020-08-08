package com.example.app.base;

import com.example.app.fragment.screens.chooser.ChooserContract;

public interface BasePresenter<T> {
    void takeView (T view);
    void dropView ();
}
