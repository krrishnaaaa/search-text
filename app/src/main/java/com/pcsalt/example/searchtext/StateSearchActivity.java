package com.pcsalt.example.searchtext;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.pcsalt.example.searchtext.model.StateSearchResult;
import com.pcsalt.example.searchtext.presenter.StateSearchPresenter;
import com.pcsalt.example.searchtext.view.StateSearchView;

/**
 * Created by Navkrishna on 02 November, 2016
 */

public class StateSearchActivity extends AppCompatActivity implements StateSearchView {

    private StateSearchPresenter mPresenter;
    private EditText mEtSearch;
    private RecyclerView mRvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_search);

        mPresenter = new StateSearchPresenter();
        mPresenter.attachView(this);

        mEtSearch = (EditText) findViewById(R.id.et_search_state);
        mRvResult = (RecyclerView) findViewById(R.id.rv_search_state);
        mRvResult.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onSuccess(StateSearchResult stateSearchResult) {

    }

    @Override
    public void onFail() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public Context getContext() {
        return this;
    }

    public void search(View view) {
    }
}
