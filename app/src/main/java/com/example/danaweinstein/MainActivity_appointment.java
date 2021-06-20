package com.example.danaweinstein;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;

public class MainActivity_appointment extends AppCompatActivity implements CalendarView.OnDateChangeListener {

    CalendarView calendar;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_appointment);

        calendar = findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(this);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.calenderTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView calendarView, int i0, int i1, int i2) {
        if (spinner.getSelectedItemPosition() == 0) {
            Intent i = new Intent(this, MainActivity_appointments_list.class);
            i.putExtra("year", i0);
            i.putExtra("month", i1);
            i.putExtra("day", i2);
            i.putExtra("mani_id",getIntent().getIntExtra("mani_id",0));
            i.putExtra("client_id",getIntent().getIntExtra("client_id",0));
            startActivity(i);
        }
        if (spinner.getSelectedItemPosition() == 1){
            Intent i = new Intent(this, MainActivity_view_appointments.class);
            i.putExtra("year", i0);
            i.putExtra("month", i1);
            i.putExtra("day", i2);
            i.putExtra("mani_id",getIntent().getIntExtra("mani_id",0));
            i.putExtra("client_id",getIntent().getIntExtra("client_id",0));
            startActivity(i);
        }
    }

    public void BackOnClick(View view) {
    //זמני:
        Intent BackPage = new Intent(this,MainActivity_home_mani.class);
        startActivity(BackPage);
    }
}
