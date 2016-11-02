package com.pcsalt.example.searchtext.view;

import com.pcsalt.example.searchtext.model.IPSearchResult;

/**
 * Created by Navkrishna on 03 November, 2016
 */

public interface IPSearchView extends View {
    void onSuccess(IPSearchResult ipSearchResult);

    void onFail(String message);

    void showProgress();

    void hideProgress();
}
