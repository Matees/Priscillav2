package com.example.matej.priscilla_v2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class CourseStatus {

    @SerializedName("new")
    @Expose
    private String _new;
    @SerializedName("opened")
    @Expose
    private String opened;
    @SerializedName("finished")
    @Expose
    private String finished;
    @SerializedName("course is chosen")
    @Expose
    private String courseIsChosen;

    public String getNew() {
        return _new;
    }

    public void setNew(String _new) {
        this._new = _new;
    }

    public String getOpened() {
        return opened;
    }

    public void setOpened(String opened) {
        this.opened = opened;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    public String getCourseIsChosen() {
        return courseIsChosen;
    }

    public void setCourseIsChosen(String courseIsChosen) {
        this.courseIsChosen = courseIsChosen;
    }

}
