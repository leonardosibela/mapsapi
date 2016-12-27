package com.leonardo.mapsapi.api;

import android.location.Location;

import com.leonardo.mapsapi.model.Adress;
import com.leonardo.mapsapi.model.DistanceMatrixReturn;
import com.leonardo.mapsapi.model.ReverseGeocodingReturn;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class GoogleAPI {

    private static final String ENDPOINT_URL = "https://maps.googleapis.com/";

    private static GoogleDistanceMatrixService googleDistanceMatrixService;

    public interface GoogleDistanceMatrixService {

        @GET("maps/api/distancematrix/json")
        Call<DistanceMatrixReturn> getDistanceMatrix(@Query("origins") Place origin, @Query("destinations") Place destination, @Query("key") String key);

        @GET("maps/api/geocode/json")
        Call<ReverseGeocodingReturn> getAdress(@Query("latlng") Place place, @Query("key") String key);
    }

    public static GoogleDistanceMatrixService getInstance() {

        if (googleDistanceMatrixService == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT_URL)
                    .client(new OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            googleDistanceMatrixService = retrofit.create(GoogleDistanceMatrixService.class);
        }

        return googleDistanceMatrixService;
    }

    public static class Place {

        private double latitude;
        private double longitude;

        private Place(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public Place(Location location) {
            this(location.getLatitude(), location.getLongitude());
        }

        public Place(Adress adress) {
            this(adress.getLatitude(), adress.getLongitude());
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