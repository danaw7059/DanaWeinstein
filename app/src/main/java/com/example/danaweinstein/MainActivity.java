package com.example.danaweinstein;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*************/
        /*
        Dal dal = new Dal(this);
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.image2);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitMapData = stream.toByteArray();
        Design new_design = new Design(0,1,"Light,Summer,Fancy,Sparkle",bitMapData,"dani","image2");
        dal.addNewPolishNail(new_design);
        */

    }

    public void LoginOnClick(View view) {
        Intent loginPage = new Intent(this, MainActivity_login.class);
        startActivity(loginPage);
    }

    public void RegisterOnClick(View view) {
        Intent registerPage = new Intent(this, MainActivity_register.class);
        startActivity(registerPage);
    }
}

