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
    Dal dal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        username = findViewById(R.id.editTextTextUserName3);
        password = findViewById(R.id.editTextTextPassword3);
        dal =new Dal(this);
    }

    public void login(View view) {
        String stUsername =username.getText().toString();
        String stPassword = password.getText().toString();


        if(dal.checkForAccount(stUsername,stPassword) )
        {
            if(dal.checkAccountType((dal.getAccount(stUsername)).getId()))
            {
                Intent manicuristHomePage = new Intent(this, MainActivity_home_mani.class);
                manicuristHomePage.putExtra("mani_id",dal.getManicuristByAccountId(dal.getAccount(stUsername).getId()).getId());
                startActivity(manicuristHomePage);
            }
            else
            {
                Intent clientHomePage = new Intent(this, MainActivity_home_client.class);
                clientHomePage.putExtra("client_id",dal.getClientByAccountId(dal.getAccount(stUsername).getId()).getId());
                startActivity(clientHomePage);
            }

        }
        else
        {
            Toast.makeText(this,"Username or password are wrong",Toast.LENGTH_SHORT).show();
        }


    }
}