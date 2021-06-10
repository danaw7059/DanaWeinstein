package com.example.danaweinstein;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity_find_design extends AppCompatActivity {

    CheckBox light;
    CheckBox dark;
    CheckBox winter;
    CheckBox summer;
    CheckBox sparkle;
    CheckBox fancy;
    ArrayList<String> designs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_find_design);

        light = findViewById(R.id.checkBox1);
        dark = findViewById(R.id.checkBox2);
        winter = findViewById(R.id.checkBox3);
        summer = findViewById(R.id.checkBox4);
        sparkle = findViewById(R.id.checkBox5);
        fancy = findViewById(R.id.checkBox6);
        designs = new ArrayList<String>();

    }

    public void find(View view) {

        if (light.isChecked()) {
        designs.add("light");
        }
        if (dark.isChecked()) {
            designs.add("dark");
        }
        if (winter.isChecked()) {
            designs.add("winter");
        }
        if (summer.isChecked()) {
            designs.add("summer");
        }
        if (sparkle.isChecked()) {
            designs.add("sparkle");
        }
        if (fancy.isChecked()) {
            designs.add("fancy");
        }
        String str = "Designs: ";
        int size = designs.size();
        for(int i = 0; i < size- 1; i++){
            str += designs.remove(0);
            str += ", ";
        }
        str += designs.remove(0);
        Toast.makeText(this,str ,Toast.LENGTH_SHORT).show();

        //חלון קופץ של התמונה של הלק שנמצא
    }

    public void BackOnClick(View view) {
        //זמני
        Intent BackPage = new Intent(this,MainActivity_home_mani.class);
        startActivity(BackPage);
    }

    public void OnClickfind(View view) {
        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.danails);

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
                        builder.setView(image);
        builder.create().show();
    }
}