package com.leonardo.mapsapi.model;

import java.util.List;

public class ReverseGeocodingReturn {

    private List<Result> results;
    private String error_message;
    private String status;

    public List<Result> getResults() {
        return results;
    }

    public Adress getAdress() {
        return results.get(0).getAdress();
    }

    public String getErrorMessage() {
        return error_message;
    }

    public String getStatus() {
        return status;
    }

    public String getFormatedAdress() {
        return results.get(0).getFormatted_address();
    }
}
