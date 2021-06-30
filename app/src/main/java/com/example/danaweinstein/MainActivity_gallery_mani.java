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
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity_gallery_mani extends AppCompatActivity {

    TextView textview;
    Bitmap image;
    Dal dal;

    GridView gallery;
    ArrayList<GalleryPhoto> arrGalleryPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gallery_mani);

        textview = findViewById(R.id.textViewDetails);

        gallery = findViewById(R.id.gridViewGalleryMani);
        arrGalleryPhotos = new ArrayList<>();
        dal = new Dal(this);

        arrGalleryPhotos = dal.getGalleryPhotos(getIntent().getIntExtra("mani_id",0));

        GalleryPhotoAdapter galleryPhotoAdapter = new GalleryPhotoAdapter(this,R.layout.gallery_cell,arrGalleryPhotos);
        gallery.setAdapter(galleryPhotoAdapter);

        textview.setText(dal.getManiDetail(getIntent().getIntExtra("mani_id",0)));
    }
    public void onClickAddPicture(View view) {
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
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.PNG, 100, stream);

                    byte[] byteArray = stream.toByteArray();
                    dal.addImageToGallery(getIntent().getIntExtra("mani_id",0),byteArray);

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

                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        dal.addImageToGallery(getIntent().getIntExtra("mani_id",0),byteArray);

                        arrGalleryPhotos.add(new GalleryPhoto(image));
                        GalleryPhotoAdapter galleryPhotoAdapter = new GalleryPhotoAdapter(this,R.layout.gallery_cell,arrGalleryPhotos);
                        gallery.setAdapter(galleryPhotoAdapter);
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
                                image=BitmapFactory.decodeFile(picturePath);
                                cursor.close();
                            }
                        }

                    }
                    break;
            }
        }
    }
}