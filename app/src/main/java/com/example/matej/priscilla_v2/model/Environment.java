package com.example.matej.priscilla_v2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Environment {

    @SerializedName("course")
    @Expose
    private String course;
    @SerializedName("courses")
    @Expose
    private String courses;
    @SerializedName("lesson")
    @Expose
    private String lesson;
    @SerializedName("lessons")
    @Expose
    private String lessons;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("codes")
    @Expose
    private String codes;
    @SerializedName("done")
    @Expose
    private String done;
    @SerializedName("all")
    @Expose
    private String all;
    @SerializedName("points")
    @Expose
    private String points;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getLessons() {
        return lessons;
    }

    public void setLessons(String lessons) {
        this.lessons = lessons;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

}
