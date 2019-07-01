package com.example.matej.priscilla_v2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("myorder")
    @Expose
    private Integer myorder;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;

    public Category(String name) {
        this.name = name;
    }

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    private int code;
    private Throwable t;


    public Category(int code) {
        this.code = code;
    }

    public Category(Throwable t) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMyorder() {
        return myorder;
    }

    public void setMyorder(Integer myorder) {
        this.myorder = myorder;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
