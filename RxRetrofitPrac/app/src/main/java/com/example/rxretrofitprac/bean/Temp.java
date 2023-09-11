package com.example.rxretrofitprac.bean;

import com.google.gson.annotations.SerializedName;

public class Temp {
    @SerializedName("data")
    private String[] name;

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }
}
