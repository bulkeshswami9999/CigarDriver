package com.hav.cigar.driver.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VerifyOtpData implements Serializable
{

    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = -1522685036040417973L;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}