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
        AppointmentPage.putExtra("mani_id",getIntent().getIntExtra("mani_id",0));
        startActivity(AppointmentPage);
    }

    public void findDesignOnClick(View view) {
        Intent FindDesigntPage = new Intent(this, MainActivity_find_design.class);
        FindDesigntPage.putExtra("mani_id",getIntent().getIntExtra("mani_id",0));
        startActivity(FindDesigntPage);
    }

    public void addNewNailPolishOnClick(View view) {
        Intent AddNewPolishNail = new Intent(this, MainActivity_new_nail_polish.class);
        AddNewPolishNail.putExtra("mani_id",getIntent().getIntExtra("mani_id",0));
        startActivity(AddNewPolishNail);
    }

    public void onClickMyProfile(View view) {
        Intent MyProfile = new Intent(this, MainActivity_gallery_mani.class);
        MyProfile.putExtra("mani_id",getIntent().getIntExtra("mani_id",0));
        startActivity(MyProfile);
    }

    public void onClickSettingMani(View view) {
        Intent settingMani = new Intent(this, MainActivity_setting_mani.class);
        settingMani.putExtra("mani_id",getIntent().getIntExtra("mani_id",0));
        startActivity(settingMani);
    }
}