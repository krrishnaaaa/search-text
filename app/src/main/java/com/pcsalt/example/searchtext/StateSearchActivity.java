package com.pcsalt.example.searchtext;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.pcsalt.example.searchtext.adapter.StateListAdapter;
import com.pcsalt.example.searchtext.model.StateSearchResult;
import com.pcsalt.example.searchtext.presenter.StateSearchPresenter;
import com.pcsalt.example.searchtext.utils.Utils;
import com.pcsalt.example.searchtext.view.StateSearchView;

/**
 * Created by Navkrishna on 02 November, 2016
 */

public class StateSearchActivity extends AppCompatActivity implements StateSearchView {

    private StateSearchPresenter mPresenter;
    private EditText mEtSearch;
    private RecyclerView mRvResult;
    private View mProgressView, mErrorView;
    private TextView mTvError;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_search);

        mPresenter = new StateSearchPresenter();
        mPresenter.attachView(this);

        mProgressView = findViewById(R.id.ll_progress);
        mErrorView = findViewById(R.id.ll_error);
        mTvError = (TextView) findViewById(R.id.tv_message);
        mEtSearch = (EditText) findViewById(R.id.et_search_state);
        mRvResult = (RecyclerView) findViewById(R.id.rv_search_state);

        mEtSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mEtSearch.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        mEtSearch.setLines(1);
        mEtSearch.setHorizontallyScrolling(true);
        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search();
                    return true;
                }
                return false;
            }
        });

        mRvResult.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onSuccess(StateSearchResult stateSearchResult) {
        mRvResult.setAdapter(new StateListAdapter(stateSearchResult.getRestResponse().getResults()));
        mRvResult.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
    }

    @Override
    public void onFail(String message) {
        mRvResult.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
        mTvError.setText(message);
    }

    @Override
    public void showProgress() {
        mRvResult.setVisibility(View.GONE);
        mProgressView.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        mProgressView.setVisibility(View.GONE);
    }

    @Override
    public Context getContext() {
        return this;
    }

    public void search() {
        Utils.hideKeyboard(getContext(), mEtSearch);
        mPresenter.searchState(Utils.getText(mEtSearch));
    }
}
