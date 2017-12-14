package com.dannextech.apps.eventmanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddTask extends AppCompatActivity implements View.OnClickListener{

    private Button btSubmit;
    private EditText etTitle,etDescription;
    TextView etDate,etTime,etRemind;

    EventDbQueries query;

    private static final int timeId=0, remindId=1;
    private static final String TAG = "Edit Text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        query = new EventDbQueries(this);

        etTitle = (EditText) findViewById(R.id.etTTitle);
        etDescription = (EditText) findViewById(R.id.etTDescription);
        etDate = (TextView) findViewById(R.id.etTDate);
        etTime = (TextView) findViewById(R.id.etTTime);
        etRemind = (TextView) findViewById(R.id.etTRemind);
        btSubmit = (Button) findViewById(R.id.btTSubmit);

        etDate.setKeyListener(null);
        etTime.setKeyListener(null);
        etRemind.setKeyListener(null);

        btSubmit.setOnClickListener(this);
        etDate.setOnClickListener(this);
        etTime.setOnClickListener(this);
        etRemind.setOnClickListener(this);
    }

    private void showTimePicker(final int id) {
        int mHour, mMinute;
        final Calendar cal = Calendar.getInstance();
        mHour = cal.get(Calendar.HOUR);
        mMinute = cal.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(AddTask.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (id == timeId)
                    setTime(hourOfDay,minute);
                else
                    setRemind(hourOfDay,minute);
            }
        },mHour,mMinute,false);
        timePickerDialog.show();
    }

    private void setRemind(int hourOfDay, int minute) {
        etRemind.setText(hourOfDay+":"+minute);
    }

    private void setTime(int hourOfDay, int minute) {
        etTime.setText(hourOfDay+":"+minute);
    }


    private void showDatePicker() {
        int mYear,mMonth,mDay;
        Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddTask.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(AddTask.this,year+"/"+(month+1)+"/"+dayOfMonth,Toast.LENGTH_SHORT).show();
                setDate(year,month,dayOfMonth);
            }
        },mYear,mMonth,mDay);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
        datePickerDialog.show();
    }

    private void setDate(int year, int month, int dayOfMonth) {
        etDate.setText(year+"/"+(month+1)+"/"+dayOfMonth);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.etTDate:
                showDatePicker();
                break;
            case R.id.etTTime:
                showTimePicker(0);
                break;
            case R.id.etTRemind:
                showTimePicker(1);
                break;
            case R.id.btTSubmit:
                Toast.makeText(getApplicationContext(),"Submit was clicked",Toast.LENGTH_SHORT).show();
                saveEventDetails();
        }
    }

    private void saveEventDetails() {
        String title = null,description = null,venue = null,date = null,time = null,remind = null;
        if (etTitle.getText().toString().isEmpty())
            etTitle.setError("Title is required");
        else
            title = etTitle.getText().toString();
        if (etDescription.getText().toString().isEmpty())
            etDescription.setError("Description is required");
        else
            description = etDescription.getText().toString();
        if (etDate.getText().toString().isEmpty())
            etDate.setError("Date is required");
        else
            date = etDate.getText().toString();
        if (etTime.getText().toString().isEmpty())
            etTime.setError("Time is required");
        else
            time = etTime.getText().toString();
        if (etRemind.getText().toString().isEmpty())
            etRemind.setError("Remind me At is required");
        else
            remind = etRemind.getText().toString();

        query.saveTask(title,description,date,time,remind);

        startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }
}
