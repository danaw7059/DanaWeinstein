package com.example.danaweinstein;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity_home_mani extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_mani);
    }

    public void AppointmentOnClick(View view) {
        Intent AppointmentPage = new Intent(this, MainActivity_appointment.class);
        startActivity(AppointmentPage);
    }

    public void findDesignOnClick(View view) {
        Intent FindDesigntPage = new Intent(this, MainActivity_find_design.class);
        startActivity(FindDesigntPage);
    }

    public void addNewNailPolishOnClick(View view) {
        Intent AddNewPolishNail = new Intent(this, MainActivity_new_nail_polish.class);
        startActivity(AddNewPolishNail);
    }

    public void BackOnClick(View view) {
    }

    public void onClickMyProfile(View view) {
        Intent MyProfile = new Intent(this, MainActivity_gallery_mani.class);
        startActivity(MyProfile);
    }
}