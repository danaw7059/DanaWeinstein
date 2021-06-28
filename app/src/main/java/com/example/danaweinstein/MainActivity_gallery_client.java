package com.example.danaweinstein;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity_gallery_client extends AppCompatActivity {

    TextView textview;
    GridView gallery;
    ArrayList<GalleryPhoto> arrGalleryPhotos;

    Bitmap image;
    Dal dal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gallery_client);

        textview = findViewById(R.id.textViewDetailsClient);

        gallery = findViewById(R.id.gridViewGalleryClient);
        arrGalleryPhotos = new ArrayList<>();

        dal= new Dal(this);

        arrGalleryPhotos = dal.getGalleryPhotos(dal.getClientByClientId(getIntent().getIntExtra("client_id", 0)).getMani_id());

        GalleryPhotoAdapter galleryPhotoAdapter = new GalleryPhotoAdapter(this,R.layout.gallery_cell,arrGalleryPhotos);
        gallery.setAdapter(galleryPhotoAdapter);

        textview.setText(dal.getManiDetail(dal.getClientByClientId(getIntent().getIntExtra("client_id", 0)).getMani_id()));

    }

    private void getGalleryPhotosData() {

    }

    public void BackOnClick(View view) {
    }
}