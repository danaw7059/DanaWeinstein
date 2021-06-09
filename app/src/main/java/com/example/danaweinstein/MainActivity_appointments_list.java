package com.example.danaweinstein;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity_appointments_list extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TextView textViewDate;
    ListView listViewAppointment;
    ArrayList<Appointment> arrAppointment;
    Appointment_Adapter appointmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_appointments_list);
        textViewDate = findViewById(R.id.textViewDate);
        listViewAppointment = findViewById(R.id.listViewHours);
        arrAppointment = new ArrayList<Appointment>();

        getAppointmentsData();

        appointmentAdapter = new Appointment_Adapter(this, R.layout.appointment, arrAppointment);
        listViewAppointment.setAdapter(appointmentAdapter);

        listViewAppointment.setOnItemClickListener(this);

        Intent i = getIntent();
        textViewDate.setText(textViewDate.getText() + " " + i.getExtras().getInt("day") + "/" + i.getExtras().getInt("month") + 1 + "/" + i.getExtras().getInt("year"));
    }

    private void getAppointmentsData() {
        Appointment appointment;
        for (int i = 0; i < 24; i++) {
            appointment = new Appointment(i);
            arrAppointment.add(appointment);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        appointmentAdapter.changeBackground(view, i);
    }

    public void BackOnClick(View view) {
    }
}
