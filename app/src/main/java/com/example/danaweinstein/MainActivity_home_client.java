package com.example.danaweinstein;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity_home_client extends AppCompatActivity {

    Dal dal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_client);
        dal = new Dal(this);
    }

    public void ManiProfileOnClick(View view) {
        if(dal.getClientByClientId(getIntent().getIntExtra("client_id", 0)).getMani_id() !=0)
        {
            Intent ManiProfile = new Intent(this, MainActivity_gallery_client.class);
            ManiProfile.putExtra("client_id",getIntent().getIntExtra("client_id",0));
            startActivity(ManiProfile);
        }
        else
        {
            Toast.makeText(this,"Add manicurist first",Toast.LENGTH_SHORT).show();
        }

    }

    public void MakeAppointmentOnClick(View view) {
        if(dal.getClientByClientId(getIntent().getIntExtra("client_id", 0)).getMani_id() !=0)
        {
            Intent AppointmentPage = new Intent(this, MainActivity_appointment.class);
            AppointmentPage.putExtra("client_id",getIntent().getIntExtra("client_id",0));
            startActivity(AppointmentPage);
        }
        else {
            Toast.makeText(this,"Add manicurist first",Toast.LENGTH_SHORT).show();
        }

    }

    public void FindDesignOnClick(View view) {
        if(dal.getClientByClientId(getIntent().getIntExtra("client_id", 0)).getMani_id() !=0)
        {
            Intent FindDesigntPage = new Intent(this, MainActivity_find_design.class);
            FindDesigntPage.putExtra("client_id",getIntent().getIntExtra("client_id",0));
            startActivity(FindDesigntPage);
        }
        else {
            Toast.makeText(this,"Add manicurist first",Toast.LENGTH_SHORT).show();
        }

    }
    public void onClickSettingClient(View view) {
        Intent settingClient = new Intent(this, MainActivity_setting_client.class);
        settingClient.putExtra("client_id",getIntent().getIntExtra("client_id",0));
        startActivity(settingClient);
    }
}