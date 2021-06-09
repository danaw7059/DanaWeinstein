package com.example.danaweinstein;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Appointment_Adapter extends ArrayAdapter {

    private Context ctx;
    private int appointmentResourceId;
    private List<Appointment> data;

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(this.appointmentResourceId, null);
        Appointment hour = this.data.get(position);
        TextView textViewHour = v.findViewById(R.id.textViewHour);
        textViewHour.setText(hour.toString());
        return v;
    }

    public Appointment_Adapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.ctx = context;
        this.appointmentResourceId = resource;
        this.data = objects;
    }

    public void changeBackground(View v, int position) {
        Appointment appointment = this.data.get(position);
        if (!appointment.isSelected()) {
            v.setBackgroundColor(Color.GREEN);
            appointment.setSelected(true);
        } else {
            v.setBackgroundColor(Color.argb(20,248,133,178));
            appointment.setSelected(false);
        }
    }
}
