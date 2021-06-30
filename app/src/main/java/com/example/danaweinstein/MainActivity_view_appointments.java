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
    Dal dal;
    String date;
    boolean accountType;
    TextView textViewPersonInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_appointments);
        textViewDate = findViewById(R.id.textViewDate);
        listViewHours = findViewById(R.id.listViewHours);
        arrHours = new ArrayList<Appointment>();

        textViewPersonInfo = findViewById(R.id.textViewPersonInfo);
        dal = new Dal(this);
        date= formatTime();
        accountType = getIntent().getIntExtra("client_id",0) == 0;

        getHoursData();

        hourAdapter = new Appointment_Adapter(this, R.layout.appointment, arrHours);
        listViewHours.setAdapter(hourAdapter);

        listViewHours.setOnItemClickListener(this);

        Intent i = getIntent();
        textViewDate.setText(textViewDate.getText() + " " + i.getExtras().getInt("day") + "/" + (i.getExtras().getInt("month") + 1) + "/" + i.getExtras().getInt("year"));
    }
    private void getHoursData() {
        ArrayList<Meeting> arrMeetings;
        if (accountType)
            arrMeetings = dal.getManicuristMeetingsByDay(getIntent().getIntExtra("mani_id", 0), date);
        else
            arrMeetings = dal.getClientMeetingsByDay(getIntent().getIntExtra("client_id", 0), date);
        Appointment hour;
        for (int i = 0; i < arrMeetings.size(); i++) {
            hour = new Appointment(Integer.parseInt(arrMeetings.get(i).getDate().substring(11)));
            arrHours.add(hour);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (accountType)
            textViewPersonInfo.setText("Meet with: " + dal.getClientByClientId(dal.getClientIdByMeeting(getIntent().getIntExtra("mani_id", 0), date + "/" + hourAdapter.getHour(i).getStringHour())).getFull_name());
        else
            textViewPersonInfo.setText("Meet with: " + dal.getManicuristById(dal.getManicuristIdByMeeting(getIntent().getIntExtra("client_id", 0), date + "/" + hourAdapter.getHour(i).getStringHour())).getFull_name());
    }

    public String formatTime() {
        String st = "";
        int year = getIntent().getIntExtra("year", 0);
        int month = getIntent().getIntExtra("month", 0);
        int day = getIntent().getIntExtra("day", 0);

        if (day < 10)
            st += "0";
        st += day + "/";
        if (month < 10)
            st += "0";
        st += month + "/" + year;

        return st;
    }
}