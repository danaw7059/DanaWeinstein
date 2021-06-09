package com.example.danaweinstein;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity_view_appointments extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TextView textViewDate;
    ListView listViewHours;
    ArrayList<Appointment> arrHours;
    Appointment_Adapter hourAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_appointments);
        textViewDate = findViewById(R.id.textViewDate);
        listViewHours = findViewById(R.id.listViewHours);
        arrHours = new ArrayList<Appointment>();

        getHoursData();

        hourAdapter = new Appointment_Adapter(this, R.layout.appointment, arrHours);
        listViewHours.setAdapter(hourAdapter);

        listViewHours.setOnItemClickListener(this);

        Intent i = getIntent();
        textViewDate.setText(textViewDate.getText() + " " + i.getExtras().getInt("day") + "/" + i.getExtras().getInt("month") + 1 + "/" + i.getExtras().getInt("year"));
    }
    private void getHoursData() {
        Appointment hour;
        for (int i = 0; i < 24; i++) {
            hour = new Appointment(i);
            arrHours.add(hour);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        hourAdapter.changeBackground(view, i);
    }
}