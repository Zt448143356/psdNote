package com.example.psdnote.model;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("status")
    private int status;
    @SerializedName("msg")
    private String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

}
