package com.pcsalt.example.searchtext.dataservice;

import com.pcsalt.example.searchtext.model.IPSearchResult;
import com.pcsalt.example.searchtext.model.StateSearchResult;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Navkrishna on 02 November, 2016
 */

public interface ApiService {

    @GET("state/search/IND")
    Observable<StateSearchResult> searchState(@Query("text") String name);

    @GET("{ip}/json")
    Observable<IPSearchResult> searchIP(@Path("ip") String ip);

    class Factory {
        private static ApiService stateSearchService;
        private static ApiService ipSearchService;

        public static ApiService getStateSearchService() {
            if (stateSearchService == null) {
                stateSearchService = createApiService("http://services.groupkt.com/");
            }
            return stateSearchService;
        }

        public static ApiService getIpSearchService() {
            if (ipSearchService == null) {
                ipSearchService = createApiService("http://geo.groupkt.com/ip/");
            }
            return ipSearchService;
        }

        private static ApiService createApiService(String url) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .addInterceptor(interceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(ApiService.class);
        }
    }
}
