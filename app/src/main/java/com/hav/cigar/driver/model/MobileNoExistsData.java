package com.hav.cigar.driver.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MobileNoExistsData implements Serializable
{

    @SerializedName("message")
    @Expose
     String message;
    @SerializedName("id")
    @Expose
     String id="";
    @SerializedName("full_name")
    @Expose
     String fullName="";
    @SerializedName("email_id")
    @Expose
     String emailId="";
    @SerializedName("mob")
    @Expose
     String mob;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

}
