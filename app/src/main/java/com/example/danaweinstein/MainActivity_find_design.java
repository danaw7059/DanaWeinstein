package com.example.danaweinstein;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.zip.Inflater;

public class MainActivity_find_design extends AppCompatActivity {

    CheckBox light;
    CheckBox dark;
    CheckBox winter;
    CheckBox summer;
    CheckBox sparkle;
    CheckBox fancy;
    ArrayList<String> designs;
    ArrayList<Design> connected;

    Dal dal;

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

        dal = new Dal(this);

    }

    public void OnClickfind(View view) {

        if (light.isChecked()) {
        designs.add("Light");
        }
        if (dark.isChecked()) {
            designs.add("Dark");
        }
        if (winter.isChecked()) {
            designs.add("Winter");
        }
        if (summer.isChecked()) {
            designs.add("Summer");
        }
        if (sparkle.isChecked()) {
            designs.add("Sparkle");
        }
        if (fancy.isChecked()) {
            designs.add("Fancy");
        }
        String strdesigns = "";
        int size = designs.size();
        for(int i = 0; i < size - 1; i++){
            strdesigns += designs.remove(0);
            strdesigns += ",";
        }
        strdesigns += designs.remove(0);
        Toast.makeText(this,strdesigns ,Toast.LENGTH_SHORT).show();

        connected = dal.findNewPolishNail(getIntent().getIntExtra("mani_id",0),strdesigns);
        Toast.makeText(this, "" +connected.size(), Toast.LENGTH_SHORT).show();
        Random rnd = new Random();
        int index=0;
        index = rnd.nextInt(connected.size());



        //חלון קופץ של התמונה של הלק שנמצא

        LayoutInflater linf = LayoutInflater.from(this);
        View inflater = linf.inflate(R.layout.find_design,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Your Chosen Design:");
        final View customLayout = getLayoutInflater().inflate(R.layout.find_design, null);

        builder.setView(inflater);
        ImageView imageView = inflater.findViewById(R.id.findDesignImage);
        TextView textCompany = inflater.findViewById(R.id.TextCompany);
        TextView textCodePolishNail = inflater.findViewById(R.id.TextCodePolishNail);
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(connected.get(index).getImage(),0,connected.get(index).getImage().length));
        textCompany.setText("Company name: " + connected.get(index).getCompany_name());
        textCodePolishNail.setText("Polish Nail Code: " + connected.get(index).getPolish_nail_code());
        builder.setPositiveButton("Close",null);
        builder.show();
    }
/*
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
    */

}