package com.example.matej.priscilla_v2.bodies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OauthBody {
    @SerializedName("grant_type")
    @Expose
    private String grantType;
    @SerializedName("client_id")
    @Expose
    private Integer clientId;
    @SerializedName("client_secret")
    @Expose
    private String clientSecret;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;

    public OauthBody(String grantType, Integer clientId, String clientSecret, String username, String password) {
        this.grantType = grantType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.username = username;
        this.password = password;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
