package com.example.matej.priscilla_v2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Register {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("role_id")
    @Expose
    private Integer roleId;
    @SerializedName("country_id")
    @Expose
    private Integer countryId;
    @SerializedName("pref_lang_id")
    @Expose
    private Integer prefLangId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getPrefLangId() {
        return prefLangId;
    }

    public void setPrefLangId(Integer prefLangId) {
        this.prefLangId = prefLangId;
    }
}
