package com.streamliners.sendingobjects;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.streamliners.sendingobjects.ModelClasses.Student;
import com.streamliners.sendingobjects.databinding.ActivityObjectSenderBinding;

import java.util.Objects;

public class ObjectSenderActivity extends AppCompatActivity {

    //create object for view binding
    ActivityObjectSenderBinding b;

    /**
     * It initialises the activity.
     * @param savedInstanceState : reference to a Bundle object that is passed into the onCreate method of every Android Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setupLayout();

        setupHideErrorForEditText();


        SharedPreferences prefs = getPreferences(MODE_PRIVATE);

        b.nameET.setText(prefs.getString(Constants.NAME, ""));
        b.genderRGrp.check(prefs.getInt(Constants.GENDER_TYPE, -1));
        b.rollNoET.setText(prefs.getString(Constants.ROLL_NO, ""));
        b.phoneNoET.setText(prefs.getString(Constants.PHONE_NO, ""));

    }




    /**
     * To set the layout of the ObjectSenderActivity
     */
    private void setupLayout() {
        b = ActivityObjectSenderBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        setTitle("Save Details");
    }



    /**
     * To set text watchers to the text fields and hide the errors on changing of the text
     */
    private void setupHideErrorForEditText() {

        // TextWatcher for name field
        Objects.requireNonNull(b.nameTIL.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                b.nameTIL.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /*
        // TextWatcher for gender field
        int lastChildPos = b.genderRGrp.getChildCount() - 1;
        if (b.genderRGrp.getCheckedRadioButtonId() == R.id.maleRBtn || b.genderRGrp.getCheckedRadioButtonId() == R.id.femaleRBtn){
            ((RadioButton)b.genderRGrp.getChildAt(lastChildPos)).setError(null);
        }
        */

        // TextWatcher for rollNo field
        Objects.requireNonNull(b.rollNoTIL.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                b.rollNoTIL.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // TextWatcher for phoneNo field
        Objects.requireNonNull(b.phoneNoTIL.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                b.phoneNoTIL.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }



    /**
     * Called when a view(save button here) has been clicked.
     * @param view : actual view (button in this case) that was clicked
     */
    public void saveData(View view) {

        //get info of student
        Student student = saveInfo();

        if (student == null) {
            return;
        }

        //send student object as intent
        //Intent intent = new Intent(fromClass.this,toClass.class).putExtra("myCustomerObj",customerObj);
        Intent intent = new Intent(this, ObjectViewerActivity.class);
        intent.putExtra("studentObject", student);
        startActivity(intent);
    }



    /**
     * To save the info of the student
     * @return object student of the class Student
     */
    private Student saveInfo(){

        //validating user input using RegEx (check if input is name or rollNo or phoneNo)

        //name
        String name = Objects.requireNonNull(b.nameTIL.getEditText()).getText().toString().trim();
        if (name.isEmpty()){
            b.nameTIL.setError("Please enter your name!");
            return null;
        }
        else if (!name.matches("^[A-Za-z\\s]+[.]?[A-Za-z\\s]*$")){
            b.nameTIL.setError("Invalid Format!");
            return null;
        }

        //gender
        int genderType = b.genderRGrp.getCheckedRadioButtonId();
        //int lastChildPos = b.genderRGrp.getChildCount()-1;
        if (genderType == -1){
            //((RadioButton)b.genderRGrp.getChildAt(lastChildPos)).setError("Please select Gender!");
            Toast.makeText(this, "Please select Gender!", Toast.LENGTH_SHORT).show();
            return null;
        }

        //rollNo
        String rollNo = Objects.requireNonNull(b.rollNoTIL.getEditText()).getText().toString().trim();
        if (rollNo.isEmpty()){
            b.rollNoTIL.setError("Please enter your Roll No.!");
            return null;
        }
        else if (!rollNo.matches("^\\d{2}(?i)[a-z]{3,}(?-i)\\d+$")){
            b.rollNoTIL.setError("Invalid Roll No!");
            return null;
        }

        //phoneNo
        String phoneNo = Objects.requireNonNull(b.phoneNoTIL.getEditText()).getText().toString().trim();
        if (phoneNo.isEmpty()){
            b.phoneNoTIL.setError("Please enter your Phone No.!");
            return  null;
        }
        else if (!phoneNo.matches("^\\d{10}$")){
            b.phoneNoTIL.setError("Invalid Phone No!");
            return null;
        }


        //send data for male
        Student student;
        String gender;
        if (genderType == R.id.maleRBtn){
            gender = "Male";
        }

        //send data for female
        else{
            gender = "Female";
        }
        student = new Student(name, gender, rollNo, phoneNo);
        return student;
    }


    //Instance State

    /**
     * To save the data when the activity is in Pause state
     */
    @Override
    protected void onPause() {
        super.onPause();

        //create preferences reference i.e create object of preferences
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);

        //save and commit data
        prefs.edit()
                .putString(Constants.NAME, b.nameET.getText().toString().trim())
                .putInt(Constants.GENDER_TYPE, b.genderRGrp.getCheckedRadioButtonId())
                .putString(Constants.ROLL_NO, b.rollNoET.getText().toString().trim())
                .putString(Constants.PHONE_NO, b.phoneNoET.getText().toString().trim())
                .apply();
    }
}
