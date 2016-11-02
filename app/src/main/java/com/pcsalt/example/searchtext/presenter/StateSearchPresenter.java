package com.pcsalt.example.searchtext.presenter;

import android.util.Log;

import com.pcsalt.example.searchtext.dataservice.ApiService;
import com.pcsalt.example.searchtext.model.StateSearchResult;
import com.pcsalt.example.searchtext.utils.Utils;
import com.pcsalt.example.searchtext.view.StateSearchView;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Navkrishna on 02 November, 2016
 */

public class StateSearchPresenter implements Presenter<StateSearchView> {

    private static final String TAG = "StateSearchPresenter";
    private StateSearchView mView;
    private Subscription mSubscription;
    private StateSearchResult mResult;

    @Override
    public void attachView(StateSearchView view) {
        this.mView = view;
    }

    public void searchState(String name) {
        mView.showProgress();
        if (mSubscription != null) mSubscription.unsubscribe();

        ApiService apiService = ApiService.Factory.getStateSearchService();

        mSubscription = apiService.searchState(name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<StateSearchResult>() {
                    @Override
                    public void onCompleted() {
                        mView.hideProgress();
                        Log.d(TAG, mResult.getRestResponse().toString());
                        if (mResult.getRestResponse().getResults().isEmpty()) {
                            mView.onFail(mResult.getRestResponse().getMessage().get(1));
                        } else {
                            mView.onSuccess(mResult);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        mView.hideProgress();
                        mView.onFail(Utils.errorHandleResponse(throwable, mView.getContext()));
                    }

                    @Override
                    public void onNext(StateSearchResult stateSearchResult) {
                        mResult = stateSearchResult;
                    }
                });
    }
}
