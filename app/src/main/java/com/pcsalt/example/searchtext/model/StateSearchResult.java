package com.pcsalt.example.searchtext.model;

import com.pcsalt.example.searchtext.model.state.Result;

import java.util.List;

/**
 * Created by Navkrishna on 02 November, 2016
 */

public class StateSearchResult {
    private String message;
    private List<Result> results;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
