package com.leonardo.mapsapi.api;

import com.leonardo.mapsapi.model.DistanceMatrixReturn;
import com.leonardo.mapsapi.model.ReverseGeocodingReturn;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class GoogleMapsAPI {

    private static final String ENDPOINT_URL = "https://maps.googleapis.com/";

    private static GoogleMapsService googleMapsService;

    public interface GoogleMapsService {

        @GET("maps/api/distancematrix/json")
        Call<DistanceMatrixReturn> getDistanceMatrix(@Query("origins") Place origin, @Query("destinations") Place destination, @Query("key") String key);

        @GET("maps/api/geocode/json")
        Call<ReverseGeocodingReturn> getAdress(@Query("latlng") Place place, @Query("key") String key);
    }

    public static GoogleMapsService getInstance() {

        if (googleMapsService == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT_URL)
                    .client(new OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            googleMapsService = retrofit.create(GoogleMapsService.class);
        }

        return googleMapsService;
    }

    public static class Place {

        private double latitude;
        private double longitude;

        public Place(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        @Override
        public String toString() {
            return latitude + "," + longitude;
        }
    }
}