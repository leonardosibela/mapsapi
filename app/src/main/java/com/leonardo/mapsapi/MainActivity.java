package com.leonardo.mapsapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.leonardo.mapsapi.api.GoogleMapsAPI;
import com.leonardo.mapsapi.model.DistanceMatrixReturn;
import com.leonardo.mapsapi.model.ReverseGeocodingReturn;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String MAPS_API_KEY = "voodoo é pra jacu";

    @BindView(R.id.latitude)
    EditText latitude;

    @BindView(R.id.longitude)
    EditText longitude;

    @BindView(R.id.place_name)
    TextView placeName;

    @BindView(R.id.distance)
    TextView distance;

    @BindView(R.id.duration)
    TextView duration;

    @BindView(R.id.get_info_button)
    Button getInfoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.get_info_button)
    public void onGetInfoClicked(Button button) {
        double latitude = Double.valueOf(this.latitude.getText().toString());
        double longitude = Double.valueOf(this.longitude.getText().toString());
        GoogleMapsAPI.Place place = new GoogleMapsAPI.Place(latitude, longitude);

        getPlaceName(place);
        getDistanceToCampinas(place);
    }

    private void getDistanceToCampinas(GoogleMapsAPI.Place place) {
        GoogleMapsAPI.GoogleMapsService gdmAPI = GoogleMapsAPI.getInstance();

        Call<DistanceMatrixReturn> distanceMatrix = gdmAPI.getDistanceMatrix(place, new GoogleMapsAPI.Place(-22.909883, -47.062581), MAPS_API_KEY);
        distanceMatrix.enqueue(new Callback<DistanceMatrixReturn>() {
            @Override
            public void onResponse(Call<DistanceMatrixReturn> call, Response<DistanceMatrixReturn> response) {
                DistanceMatrixReturn distanceMatrix = response.body();
                duration.setText(distanceMatrix.getDurationString());
                distance.setText(distanceMatrix.getDistanceString());
            }

            @Override
            public void onFailure(Call<DistanceMatrixReturn> call, Throwable t) {

            }
        });
    }

    private void getPlaceName(GoogleMapsAPI.Place place) {
        GoogleMapsAPI.GoogleMapsService gdmAPI = GoogleMapsAPI.getInstance();
        Call<ReverseGeocodingReturn> paths = gdmAPI.getAdress(place, MAPS_API_KEY);

        paths.enqueue(new Callback<ReverseGeocodingReturn>() {

            @Override
            public void onResponse(Call<ReverseGeocodingReturn> call, Response<ReverseGeocodingReturn> response) {
                ReverseGeocodingReturn reverseGeocodingReturn = response.body();
                placeName.setText(reverseGeocodingReturn.getFormatedAdress());
            }

            @Override
            public void onFailure(Call<ReverseGeocodingReturn> call, Throwable t) {

            }
        });
    }
}
