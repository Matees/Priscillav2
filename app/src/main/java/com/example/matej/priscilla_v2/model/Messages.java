package com.example.matej.priscilla_v2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Messages {

    @SerializedName("course is not chosen")
    @Expose
    private String courseIsNotChosen;

    public String getCourseIsNotChosen() {
        return courseIsNotChosen;
    }

    public void setCourseIsNotChosen(String courseIsNotChosen) {
        this.courseIsNotChosen = courseIsNotChosen;
    }

}
