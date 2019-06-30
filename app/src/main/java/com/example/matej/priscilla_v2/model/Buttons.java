package com.example.matej.priscilla_v2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Buttons {

    @SerializedName("courses")
    @Expose
    private String courses;
    @SerializedName("competitions")
    @Expose
    private String competitions;
    @SerializedName("leaderboard")
    @Expose
    private String leaderboard;
    @SerializedName("practice")
    @Expose
    private String practice;
    @SerializedName("discussions")
    @Expose
    private String discussions;
    @SerializedName("continue")
    @Expose
    private String _continue;

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getCompetitions() {
        return competitions;
    }

    public void setCompetitions(String competitions) {
        this.competitions = competitions;
    }

    public String getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(String leaderboard) {
        this.leaderboard = leaderboard;
    }

    public String getPractice() {
        return practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }

    public String getDiscussions() {
        return discussions;
    }

    public void setDiscussions(String discussions) {
        this.discussions = discussions;
    }

    public String getContinue() {
        return _continue;
    }

    public void setContinue(String _continue) {
        this._continue = _continue;
    }

}
