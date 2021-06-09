package com.example.danaweinstein;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity_login extends AppCompatActivity {

    EditText username;
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        username = findViewById(R.id.editTextTextUserName3);
        password = findViewById(R.id.editTextTextPassword3);
    }



    public void BackOnClick(View view) {
        Intent BackPage = new Intent(this,MainActivity.class);
        startActivity(BackPage);
    }

    public void login(View view) {
        String stUsername =username.getText().toString();
        String stPassword = password.getText().toString();

        Toast.makeText(this,"username: " + stUsername + " password: " + stPassword,Toast.LENGTH_SHORT).show();

        Intent manicuristHomePage = new Intent(this, MainActivity_home_mani.class);
        startActivity(manicuristHomePage);
    }
}