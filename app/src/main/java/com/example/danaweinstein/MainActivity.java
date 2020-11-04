package com.example.danaweinstein;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void LoginOnClick(View view) {
        Intent loginPage = new Intent(this,MainActivity2.class);
        startActivity(loginPage);
    }

    public void RegisterOnClick(View view) {
        Intent registerPage = new Intent(this,MainActivity3.class);
        startActivity(registerPage);
    }
}