package com.example.stocktracker.POJO.SearchResponse;

import com.example.stocktracker.POJO.SearchResponse.Results;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResultSet {
    @JsonProperty("Query")
    private String Query;
    @JsonProperty("Result")
    private List<Results> Result;
    public ResultSet(){}
    public ResultSet(String query, List<Results> result) {
        this.Query = query;
        this.Result = result;
    }

    public String getQuery() {
        return Query;
    }

    public void setQuery(String query) {
        this.Query = query;
    }

    public List<Results> getResult() {
        return Result;
    }

    public void setResult(List<Results> result) {
        this.Result = result;
    }
}
