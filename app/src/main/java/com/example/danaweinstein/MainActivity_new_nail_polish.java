package com.example.danaweinstein;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity_new_nail_polish extends AppCompatActivity {

    EditText companyName;
    EditText polishNailCode;
    CheckBox light;
    CheckBox dark;
    CheckBox winter;
    CheckBox summer;
    CheckBox sparkle;
    CheckBox fancy;
    ArrayList<String> designs;

    Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new_nail_polish);

        companyName = findViewById(R.id.editTextCompanyName);
        polishNailCode= findViewById(R.id.editTextPolishNailCode);

        light = findViewById(R.id.checkBox1);
        dark = findViewById(R.id.checkBox2);
        winter = findViewById(R.id.checkBox3);
        summer = findViewById(R.id.checkBox4);
        sparkle = findViewById(R.id.checkBox5);
        fancy = findViewById(R.id.checkBox6);
        designs = new ArrayList<String>();
    }

    public void addOnClick(View view) {

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
        String str = "Company Name: " + companyName.getText().toString() + " , Polish Name Code: " + polishNailCode.getText().toString() + " , Designs: ";
        int size = designs.size();
        for(int i = 0; i < size - 1; i++){
            str += designs.remove(0);
            str += ", ";
        }
        str += designs.remove(0);
        Toast.makeText(this,str ,Toast.LENGTH_SHORT).show();
    }

    public void BackOnClick(View view) {
        //זמני:
        Intent BackPage = new Intent(this,MainActivity_home_mani.class);
        startActivity(BackPage);
    }

    public void addDesignOnClick(View view) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        image = selectedImage;
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                image= BitmapFactory.decodeFile(picturePath);
                                cursor.close();
                            }
                        }

                    }
                    break;
            }
        }
    }
}