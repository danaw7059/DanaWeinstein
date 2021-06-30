package com.example.danaweinstein;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity_setting_mani extends AppCompatActivity {

    TextView textView;
    Dal dal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setting_mani);

        textView =findViewById(R.id.textViewDetails2);
        dal = new Dal(this);
        ((TextView)findViewById(R.id.textView12)).setText(((TextView)findViewById(R.id.textView12)).getText().toString() + getIntent().getIntExtra("mani_id",0));
        textView.setText(dal.getManiDetail(getIntent().getIntExtra("mani_id",0)));
    }

    public void onClickEdit(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Edit Details");

        final EditText input = new EditText(this);
        input.setText(textView.getText());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        alertDialog.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText(input.getText());
                        dal.updateManicuristDetails(getIntent().getIntExtra("mani_id",0),input.getText().toString());
                        Toast.makeText(MainActivity_setting_mani.this, "Updated details", Toast.LENGTH_SHORT).show();

                    }
                });

        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();



    }
}