package com.example.danaweinstein;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity_appointments_list extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TextView textViewDate;
    ListView listViewAppointment;
    ArrayList<Appointment> arrAppointment;
    Appointment_Adapter appointmentAdapter;
    boolean accountType;
    Dal dal;
    String date;
    ArrayList<Appointment> selectedHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_appointments_list);
        textViewDate = findViewById(R.id.textViewDate);
        listViewAppointment = findViewById(R.id.listViewHours);
        arrAppointment = new ArrayList<Appointment>();
        dal = new Dal(this);
        date = formatTime();
        accountType = getIntent().getIntExtra("client_id",0) == 0;
        getHoursData();
        textViewDate.setText(textViewDate.getText().toString() + date);

        appointmentAdapter = new Appointment_Adapter(this, R.layout.appointment, arrAppointment);
        listViewAppointment.setAdapter(appointmentAdapter);

        listViewAppointment.setOnItemClickListener(this);



        selectedHours = new ArrayList<>();


    }

    private void getHoursData() {
        if(accountType)
        {
            Appointment hour;
            for (int i = 0; i < 24; i++) {
                hour = new Appointment(i);
                if (i < 10) {
                    if (dal.checkMeetings(getIntent().getIntExtra("mani_id", 0), date + "/0" + i))
                        hour.setSelected(true);
                } else if (dal.checkMeetings(getIntent().getIntExtra("mani_id", 0), date + "/" + i))
                    hour.setSelected(true);
                arrAppointment.add(hour);
            }
        }
        else
        {
            ArrayList<Meeting> meetings = dal.getManicuristEmptyMeetingsByDay(dal.getClientByClientId(getIntent().getIntExtra("client_id",0)).getMani_id(), date);
            for (Meeting meeting : meetings)
                arrAppointment.add(new Appointment(Integer.parseInt(meeting.getDate().substring(11))));
        }

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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(accountType)
        {
            appointmentAdapter.changeBackground(view, i);
        }
        else
        {
            selectedHours.add(appointmentAdapter.getHour(i));
        }

    }

    public void onClickConfirm(View view) {
        if(accountType)
        {
            dal.removeMeetings(getIntent().getIntExtra("mani_id", 0), date);
            for (int i = 0; i < arrAppointment.size(); i++)
                if (arrAppointment.get(i).isSelected())
                    dal.addMeeting(getIntent().getIntExtra("mani_id", 0), 0, date + "/" + arrAppointment.get(i).getStringHour());
        }
        else
        {
            for (int i = 0; i < selectedHours.size(); i++)
                dal.updateMeeting(dal.getClientByClientId(getIntent().getIntExtra("client_id",0)).getMani_id(), getIntent().getIntExtra("client_id", 0), date + "/" + selectedHours.get(i).getStringHour());
        }
        Toast.makeText(this, "Meeting confirm!", Toast.LENGTH_SHORT).show();
        if (accountType) {
            Intent maniHomePage = new Intent(this, MainActivity_home_mani.class);
            maniHomePage.putExtra("mani_id",getIntent().getIntExtra("mani_id",0));
            startActivity(maniHomePage);
        } else {
            Intent clientHomePage = new Intent(this, MainActivity_home_client.class);
            clientHomePage.putExtra("client_id",getIntent().getIntExtra("client_id",0));
            startActivity(clientHomePage);
        }


    }
}
