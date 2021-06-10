package com.example.danaweinstein;

import android.graphics.Bitmap;

public class GalleryPhoto {
    Bitmap image;

    public GalleryPhoto(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "GalleryPhoto{" +
                "image=" + image +
                '}';
    }
}
