package com.example.matej.priscilla_v2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Course {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("course_order")
    @Expose
    private Integer courseOrder;
    @SerializedName("course_status")
    @Expose
    private Object courseStatus;
    @SerializedName("task_finished")
    @Expose
    private Integer taskFinished;
    @SerializedName("program_finished")
    @Expose
    private Integer programFinished;
    @SerializedName("task_all")
    @Expose
    private Integer taskAll;
    @SerializedName("program_all")
    @Expose
    private Integer programAll;
    @SerializedName("start_date")
    @Expose
    private Object startDate;
    @SerializedName("finish_date")
    @Expose
    private Object finishDate;
    @SerializedName("description")
    @Expose
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCourseOrder() {
        return courseOrder;
    }

    public void setCourseOrder(Integer courseOrder) {
        this.courseOrder = courseOrder;
    }

    public Object getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(Object courseStatus) {
        this.courseStatus = courseStatus;
    }

    public Integer getTaskFinished() {
        return taskFinished;
    }

    public void setTaskFinished(Integer taskFinished) {
        this.taskFinished = taskFinished;
    }

    public Integer getProgramFinished() {
        return programFinished;
    }

    public void setProgramFinished(Integer programFinished) {
        this.programFinished = programFinished;
    }

    public Integer getTaskAll() {
        return taskAll;
    }

    public void setTaskAll(Integer taskAll) {
        this.taskAll = taskAll;
    }

    public Integer getProgramAll() {
        return programAll;
    }

    public void setProgramAll(Integer programAll) {
        this.programAll = programAll;
    }

    public Object getStartDate() {
        return startDate;
    }

    public void setStartDate(Object startDate) {
        this.startDate = startDate;
    }

    public Object getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Object finishDate) {
        this.finishDate = finishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
