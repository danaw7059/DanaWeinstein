package com.example.danaweinstein;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity_gallery_client extends AppCompatActivity {

    TextView textview;
    GridView gallery;
    ArrayList<GalleryPhoto> arrGalleryPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gallery_client);

        textview = findViewById(R.id.textViewDetailsClient);

        gallery = findViewById(R.id.gridViewGalleryClient);
        arrGalleryPhotos = new ArrayList<>();

        getGalleryPhotosData();
        GalleryPhotoAdapter galleryPhotoAdapter = new GalleryPhotoAdapter(this,R.layout.gallery_cell,arrGalleryPhotos);
        gallery.setAdapter(galleryPhotoAdapter);
    }

    private void getGalleryPhotosData() {

    }

    public void BackOnClick(View view) {
    }
}