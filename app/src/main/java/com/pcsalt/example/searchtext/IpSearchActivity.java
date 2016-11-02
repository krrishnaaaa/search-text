package com.pcsalt.example.searchtext;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pcsalt.example.searchtext.model.IPSearchResult;
import com.pcsalt.example.searchtext.model.ip.Result;
import com.pcsalt.example.searchtext.presenter.IPSearchPresenter;
import com.pcsalt.example.searchtext.utils.Utils;
import com.pcsalt.example.searchtext.view.IPSearchView;

/**
 * Created by Navkrishna on 02 November, 2016
 */

public class IpSearchActivity extends AppCompatActivity implements IPSearchView, OnMapReadyCallback {

    private IPSearchPresenter mPresenter;
    private EditText mEtSearch;
    private View mProgressView, mErrorView, mInfoView;
    private TextView mTvError, mTvIpInfo;
    private GoogleMap googleMap;
    private Marker marker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip_search);

        mPresenter = new IPSearchPresenter();
        mPresenter.attachView(this);

        mProgressView = findViewById(R.id.ll_progress);
        mErrorView = findViewById(R.id.ll_error);
        mInfoView = findViewById(R.id.ll_ip_info);
        mTvError = (TextView) findViewById(R.id.tv_message);
        mTvIpInfo = (TextView) findViewById(R.id.tv_ip_info);
        mEtSearch = (EditText) findViewById(R.id.et_search_ip);

        mEtSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mEtSearch.setInputType(InputType.TYPE_CLASS_TEXT);
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

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onSuccess(IPSearchResult ipSearchResult) {
        mErrorView.setVisibility(View.GONE);
        Result result = ipSearchResult.getRestResponse().getResult();
        String ipInfo = String.format(
                getString(R.string.txt_ip_info),
                result.getCity(), result.getState(),
                result.getCountry(), result.getPostal(),
                result.getContinent());
        mTvIpInfo.setText(ipInfo);
        mInfoView.setVisibility(View.VISIBLE);

        if (googleMap != null) {
            MarkerOptions markerOptions = new MarkerOptions().position(
                    new LatLng(result.getLatitude(), result.getLongitude()))
                    .title(result.getCity());
            if (marker != null) {
                marker.remove();
            }
            marker = googleMap.addMarker(markerOptions);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 13.0f));
        }
    }

    @Override
    public void onFail(String message) {
        mInfoView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
        mTvError.setText(message);
    }

    @Override
    public void showProgress() {
        mInfoView.setVisibility(View.GONE);
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

    private void search() {
        Utils.hideKeyboard(getContext(), mEtSearch);
        final String ip = Utils.getText(mEtSearch);
        if (ip.isEmpty()) {
            onFail("Enter some text to search");
        } else if (!Utils.isValidIp(ip)) {
            onFail("Provide valid IP address");
        } else {
            mPresenter.searchIP(ip);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }
}
