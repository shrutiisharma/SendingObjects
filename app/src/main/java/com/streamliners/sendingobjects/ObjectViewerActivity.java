package com.streamliners.sendingobjects;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.streamliners.sendingobjects.ModelClasses.Student;
import com.streamliners.sendingobjects.databinding.ActivityObjectViewerBinding;

public class ObjectViewerActivity extends AppCompatActivity {

    //create object for view binding
    ActivityObjectViewerBinding b;

    String name, gender, rollNo, phoneNo;

    /**
     * It initialises the activity.
     * @param savedInstanceState : reference to a Bundle object that is passed into the onCreate method of every Android Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupLayout();
        
        viewData();

        //restore instance state

        //get from instance state
        if (savedInstanceState != null){
            name = savedInstanceState.getString(Constants.NAME, "");
            gender = savedInstanceState.getString(Constants.GENDER_TYPE, "");
            rollNo = savedInstanceState.getString(Constants.ROLL_NO, "");
            phoneNo = savedInstanceState.getString(Constants.PHONE_NO, "");
        }
    }


    /**
     * To set the layout of the ObjectViewerActivity
     */
    private void setupLayout() {

        b = ActivityObjectViewerBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        setTitle("View Details");
    }


    /**
     * To view object data sent through intent from ObjectSenderActivity
     */
    private void viewData() {

        Intent intent = getIntent();
        Student student = intent.getExtras().getParcelable("studentObject");

        if (student == null){
            Toast.makeText(this, "No data received!", Toast.LENGTH_SHORT).show();
            return;
        }

        b.nameViewer.setText(student.getmName());
        b.genderViewer.setText(student.getmGender());
        b.rollNoViewer.setText(student.getmRollNo());
        b.phoneNoViewer.setText(student.getmPhoneNo());
    }


    //Instance State

    /**
     * To save the instance state
     * @param outState : Called to retrieve per-instance state from an activity before being killed
     *                   so that the state can be restored in onCreate(Bundle) or onRestoreInstanceState(Bundle)
     *                  (the Bundle populated by this method will be passed to both).
     *                  Bundle in which to place your saved state. This value cannot be null.
     *
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(Constants.NAME, (String) b.nameViewer.getText());
        outState.putString(Constants.GENDER_TYPE, (String) b.genderViewer.getText());
        outState.putString(Constants.ROLL_NO, (String) b.rollNoViewer.getText());
        outState.putString(Constants.PHONE_NO, (String) b.phoneNoViewer.getText());
    }

}