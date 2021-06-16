package com.example.danaweinstein;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

    public void BackOnClick(View view) {
    }

    public void onClickSet(View view) {
        if(dal.checkForManicurist(Integer.parseInt(editText.getText().toString())))
        {
            dal.updateClientManiId(getIntent().getIntExtra("client_id",0),Integer.parseInt(editText.getText().toString()));
        }
    }
}