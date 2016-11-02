package com.pcsalt.example.searchtext.presenter;

import com.pcsalt.example.searchtext.dataservice.ApiService;
import com.pcsalt.example.searchtext.model.IPSearchResult;
import com.pcsalt.example.searchtext.utils.Utils;
import com.pcsalt.example.searchtext.view.IPSearchView;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Navkrishna on 03 November, 2016
 */

public class IPSearchPresenter implements Presenter<IPSearchView> {
    private IPSearchView mView;
    private Subscription mSubscription;
    private IPSearchResult mResult;

    @Override
    public void attachView(IPSearchView view) {
        this.mView = view;
    }

    public void searchIP(String ip) {
        mView.showProgress();
        if (mSubscription != null) mSubscription.unsubscribe();

        ApiService apiService = ApiService.Factory.getIpSearchService();

        mSubscription = apiService.searchIP(ip)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<IPSearchResult>() {
                    @Override
                    public void onCompleted() {
                        mView.hideProgress();
                        if (mResult.getRestResponse().getResult().getCountryIso2() == null) {
                            mView.onFail("Invalid IP");
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
                    public void onNext(IPSearchResult stateSearchResult) {
                        mResult = stateSearchResult;
                    }
                });
    }
}
