package com.pcsalt.example.searchtext.presenter;

import com.pcsalt.example.searchtext.view.StateSearchView;

/**
 * Created by Navkrishna on 02 November, 2016
 */

public class StateSearchPresenter implements Presenter<StateSearchView> {

    private StateSearchView mView;

    @Override
    public void attachView(StateSearchView view) {
        this.mView = view;
    }

    public void searchState(String name) {
        mView.showProgress();
    }
}
