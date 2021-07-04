package com.example.danaweinstein;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity_setting_client extends AppCompatActivity {

    EditText editText;
    Dal dal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setting_client);

        editText = findViewById(R.id.editTextTextManiId);

        dal = new Dal(this);
    }
    public void onClickSet(View view) {
        if(dal.checkForManicurist(Integer.parseInt(editText.getText().toString())))
        {
            dal.updateClientManiId(getIntent().getIntExtra("client_id",0),Integer.parseInt(editText.getText().toString()));
            Toast.makeText(this, "Set id", Toast.LENGTH_SHORT).show();
            Intent homePageClient = new Intent(this, MainActivity_home_client.class);
            homePageClient.putExtra("client_id",getIntent().getIntExtra("client_id",0));
            startActivity(homePageClient);

        }
        else
        {
            Toast.makeText(this, "The id not exist!", Toast.LENGTH_SHORT).show();
        }



    }
}