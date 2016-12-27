package com.leonardo.mapsapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.latitude)
    EditText latitude;

    @BindView(R.id.longitude)
    EditText longitude;

    @BindView(R.id.place_name)
    TextView placeName;

    @BindView(R.id.distance)
    TextView distance;

    @BindView(R.id.duration)
    EditText duration;

    @BindView(R.id.get_info_button)
    Button getInfoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick(R.id.get_info_button)
    public void onGetInfoClicked(Button button) {
        button.setClickable(false);
        
    }
}
