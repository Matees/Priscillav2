package com.example.matej.priscilla_v2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Courses {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("list")
    @Expose
    private List<Course> courses = null;

    private int code;
    private Throwable t;


    public Courses(int code) {
        this.code = code;
    }

    public Courses(Throwable t) {
        this.t = t;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Throwable getT() {
        return t;
    }

    public void setT(Throwable t) {
        this.t = t;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
