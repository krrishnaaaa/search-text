package com.pcsalt.example.searchtext.model;

import com.google.gson.annotations.SerializedName;
import com.pcsalt.example.searchtext.model.state.Result;

import java.util.List;

/**
 * Created by Navkrishna on 02 November, 2016
 */

public class StateSearchResult {
    @SerializedName("RestResponse")
    private RestResponse restResponse;

    public RestResponse getRestResponse() {
        return restResponse;
    }

    public void setRestResponse(RestResponse restResponse) {
        this.restResponse = restResponse;
    }

    public class RestResponse {
        @SerializedName("messages")
        private List<String> message;

        @SerializedName("result")
        private List<Result> results;

        public List<String> getMessage() {
            return message;
        }

        public void setMessage(List<String> message) {
            this.message = message;
        }

        public List<Result> getResults() {
            return results;
        }

        public void setResults(List<Result> results) {
            this.results = results;
        }
    }
}
