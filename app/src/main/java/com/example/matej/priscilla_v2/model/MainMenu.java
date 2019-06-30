package com.example.matej.priscilla_v2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainMenu {

    @SerializedName("buttons")
    @Expose
    private Buttons buttons;
    @SerializedName("profilmenu")
    @Expose
    private Profilmenu profilmenu;
    @SerializedName("environment")
    @Expose
    private Environment environment;
    @SerializedName("course-status")
    @Expose
    private CourseStatus courseStatus;
    @SerializedName("messages")
    @Expose
    private Messages messages;

    private int code;
    private Throwable t;

    public MainMenu(int code) {
        this.code = code;
    }

    public MainMenu(Throwable t) {
        this.t = t;
    }

    public Throwable getT() {
        return t;
    }

    public void setT(Throwable t) {
        this.t = t;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Buttons getButtons() {
        return buttons;
    }

    public void setButtons(Buttons buttons) {
        this.buttons = buttons;
    }

    public Profilmenu getProfilmenu() {
        return profilmenu;
    }

    public void setProfilmenu(Profilmenu profilmenu) {
        this.profilmenu = profilmenu;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public Messages getList() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

}

//-----------------------------------com.example.Buttons.java-----------------------------------

//-----------------------------------com.example.CourseStatus.java-----------------------------------

//-----------------------------------com.example.Environment.java-----------------------------------

//-----------------------------------com.example.Messages.java-----------------------------------

//-----------------------------------com.example.Profilmenu.java-----------------------------------

