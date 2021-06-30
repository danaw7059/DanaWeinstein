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
    Dal dal;


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

        dal = new Dal(this);

    }

    public void registerOnClick(View view) {


        if(!fullName.getText().toString().matches("") && !username.getText().toString().matches("")  && !password.getText().toString().matches("") && !email.getText().toString().matches("") && types.getCheckedRadioButtonId() !=-1 )
        {
            if (!dal.checkForAccount(username.getText().toString(),password.getText().toString())){
                dal.addAccount(username.getText().toString(), password.getText().toString(), email.getText().toString());
                 String type;
                 if (types.getCheckedRadioButtonId() == R.id.radioButtonManicurist) {
                     dal.addManicurist(fullName.getText().toString(), dal.getAccount(username.getText().toString()).getId(), "");
                     Intent loginPage = new Intent(this, MainActivity_login.class);
                     startActivity(loginPage);
                 }
                else {
                dal.addClient(fullName.getText().toString(), dal.getAccount(username.getText().toString()).getId(), 0);
                Intent loginPage = new Intent(this, MainActivity_login.class);
                startActivity(loginPage);
            }

            }
            else
            {
                Toast.makeText(this,"Account already exists",Toast.LENGTH_SHORT).show();
            }

        }
        else {
            Toast.makeText(this,"Some values are empty",Toast.LENGTH_SHORT).show();
        }

    }
}