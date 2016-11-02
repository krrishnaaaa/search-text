package com.pcsalt.example.searchtext.model;

import com.google.gson.annotations.SerializedName;
import com.pcsalt.example.searchtext.model.ip.Result;

/**
 * Created by Navkrishna on 03 November, 2016
 */

public class IPSearchResult {
    @SerializedName("RestResponse")
    private RestResponse restResponse;

    public RestResponse getRestResponse() {
        return restResponse;
    }

    public void setRestResponse(RestResponse restResponse) {
        this.restResponse = restResponse;
    }

    public class RestResponse {

        @SerializedName("result")
        private Result result;

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }
    }
}
