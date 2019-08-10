package com.example.matej.priscilla_v2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profilmenu {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("submission")
    @Expose
    private String submission;
    @SerializedName("courses")
    @Expose
    private String courses;
    @SerializedName("messages")
    @Expose
    private String messages;
    @SerializedName("settings")
    @Expose
    private String settings;
    @SerializedName("logout")
    @Expose
    private String logout;

    public String [] getValues(){
        return new String[]{name, submission, courses, messages, settings, logout};
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubmission() {
        return submission;
    }

    public void setSubmission(String submission) {
        this.submission = submission;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }

}
