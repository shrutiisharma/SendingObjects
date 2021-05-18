package com.streamliners.sendingobjects.ModelClasses;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Student implements Parcelable {

   private String mName;
   private String mGender;
   private String mRollNo;
   private String mPhoneNo;

    /**
     * Constructor to construct the student class
     * @param mName     name of the student
     * @param mGender   gender of the student
     * @param mRollNo   roll no of the student
     * @param mPhoneNo  phone no of the student
     */
    public Student(String mName, String mGender, String mRollNo, String mPhoneNo) {
        this.mName = mName;
        this.mRollNo = mRollNo;
        this.mGender = mGender;
        this.mPhoneNo = mPhoneNo;
    }



    protected Student(Parcel in) {
        mName = in.readString();
        mGender = in.readString();
        mRollNo = in.readString();
        mPhoneNo = in.readString();
    }


    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };


    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }


    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mGender);
        dest.writeString(mRollNo);
        dest.writeString(mPhoneNo);
    }




    // All the getters.

    /**
     * @return name of the student
     */
    public String getmName() {
        return mName;
    }

    /**
     * @return gender of the student
     */
    public String getmGender() {
        return mGender;
    }

    /**
     * @return roll no of the student
     */
    public String getmRollNo() {
        return mRollNo;
    }

    /**
     * @return phone no of the student
     */
    public String getmPhoneNo() {
        return mPhoneNo;
    }
}
