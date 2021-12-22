package com.example.stocktracker.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResponse {
    @JsonProperty("ResultSet")
    private ResultSet resultSet;

    public SearchResponse() {
    }

    public SearchResponse(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }
}
