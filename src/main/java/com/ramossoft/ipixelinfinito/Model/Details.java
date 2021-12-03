package com.ramossoft.ipixelinfinito.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {
    @Expose
    @SerializedName("address")
    private String address;

    @Expose
    @SerializedName("tag")
    private int tag=0;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
