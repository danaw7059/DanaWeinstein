package com.example.danaweinstein;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity_register extends AppCompatActivity {

    EditText fullName;
    EditText username;
    EditText password;
    EditText email;

    RadioGroup types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);

        fullName = findViewById(R.id.editTextFullName);
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        email = findViewById(R.id.editTextEmail);
        types = findViewById(R.id.radioGrupeTypes);

    }

    public void BackOnClick(View view) {
        Intent BackPage = new Intent(this,MainActivity.class);
        startActivity(BackPage);
    }

    public void registerOnClick(View view) {
        String type;
        if (types.getCheckedRadioButtonId() == R.id.radioButtonManicurist)
            type = "Manicurist";
        else
            type = "Client";
        String str = fullName.getText().toString() + " , " + username.getText().toString() + " , " + password.getText().toString() + " , " + email.getText().toString() + " ' " + type;
        Toast.makeText(this,str ,Toast.LENGTH_SHORT).show();


        //Intent manicuristHomePage = new Intent(this, MainActivity_home_mani.class);
        //startActivity(manicuristHomePage);
        Intent clientHomePage = new Intent(this, MainActivity_home_client.class);
        startActivity(clientHomePage);
    }
}