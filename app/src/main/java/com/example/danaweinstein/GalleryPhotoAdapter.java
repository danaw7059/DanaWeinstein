package com.example.danaweinstein;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class GalleryPhotoAdapter extends ArrayAdapter {
    private Context ctx;
    private int galleryPhotoResorceID;
    private List<GalleryPhoto> data;

    public GalleryPhotoAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.ctx = context;
        this.galleryPhotoResorceID = resource;
        this.data = objects;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(this.galleryPhotoResorceID, null);
        GalleryPhoto galleryPhoto = this.data.get(position);
        ImageView imageView = v.findViewById(R.id.imageViewGallery);
        imageView.setImageBitmap(galleryPhoto.getImage());
        return v;
    }
}
