package com.mycompany.kfcomapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jason_000 on 12/2/2016.
 */

public class Student implements Parcelable {
    private String name;
    private String belt;

    public Student() {

    }

    public Student( String name, String belt) {
        this.name = name;
        this.belt = belt;
    }

    public Student( Parcel source)
    {
        name = source.readString();
        belt = source.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBelt() {
        return belt;
    }

    public void setBelt(String belt) {
        this.belt = belt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        applyDefaultValues();
        dest.writeString( name );
        dest.writeString( belt );
    }

    private void applyDefaultValues() {
        if (name == null)
            name = "";
        if (belt == null)
            belt = "";
    }

    public static Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
