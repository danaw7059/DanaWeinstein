package com.example.danaweinstein;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity_home_client extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_client);
    }

    public void ManiProfileOnClick(View view) {
        Intent ManiProfile = new Intent(this, MainActivity_gallery_client.class);
        startActivity(ManiProfile);
    }

    public void MakeAppointmentOnClick(View view) {
        Intent AppointmentPage = new Intent(this, MainActivity_appointment.class);
        startActivity(AppointmentPage);
    }

    public void FindDesignOnClick(View view) {
        Intent FindDesigntPage = new Intent(this, MainActivity_find_design.class);
        startActivity(FindDesigntPage);
    }

    public void BackOnClick(View view) {
    }
}