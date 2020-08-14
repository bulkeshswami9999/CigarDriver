package com.hav.cigar.driver.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SendOtp implements Serializable
{

    @SerializedName("status")
    @Expose
     String status;
    @SerializedName("data")
    @Expose
     SendOtpData data;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SendOtpData getData() {
        return data;
    }

    public void setData(SendOtpData data) {
        this.data = data;
    }

}