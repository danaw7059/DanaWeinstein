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
        ManiProfile.putExtra("client_id",getIntent().getIntExtra("client_id",0));
        startActivity(ManiProfile);
    }

    public void MakeAppointmentOnClick(View view) {
        Intent AppointmentPage = new Intent(this, MainActivity_appointment.class);
        AppointmentPage.putExtra("client_id",getIntent().getIntExtra("client_id",0));
        startActivity(AppointmentPage);
    }

    public void FindDesignOnClick(View view) {
        Intent FindDesigntPage = new Intent(this, MainActivity_find_design.class);
        FindDesigntPage.putExtra("client_id",getIntent().getIntExtra("client_id",0));
        startActivity(FindDesigntPage);
    }

    public void BackOnClick(View view) {
    }

    public void onClickSettingClient(View view) {
        Intent settingClient = new Intent(this, MainActivity_setting_client.class);
        settingClient.putExtra("client_id",getIntent().getIntExtra("client_id",0));
        startActivity(settingClient);
    }
}