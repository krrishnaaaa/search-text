package com.pcsalt.example.searchtext.view;

import com.pcsalt.example.searchtext.model.StateSearchResult;

/**
 * Created by Navkrishna on 02 November, 2016
 */

public interface StateSearchView extends View {
    void onSuccess(StateSearchResult stateSearchResult);

    void onFail(String message);

    void showProgress();

    void hideProgress();
}
