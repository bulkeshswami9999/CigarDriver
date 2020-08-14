package com.hav.cigar.driver.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MobileNoExists implements Serializable
{

    @SerializedName("status")
    @Expose
     String status="";
    @SerializedName("data")
    @Expose
     MobileNoExistsData mobileNoExistsData;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MobileNoExistsData getMobileNoExistsData() {
        return mobileNoExistsData;
    }

    public void setMobileNoExistsData(MobileNoExistsData mobileNoExistsData) {
        this.mobileNoExistsData = mobileNoExistsData;
    }

}