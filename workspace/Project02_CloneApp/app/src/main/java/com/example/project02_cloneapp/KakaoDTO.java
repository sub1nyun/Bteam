package com.example.project02_cloneapp;

import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class KakaoDTO implements Serializable {

    private int imgId;
    private String name, msg, date;

    public KakaoDTO(int imgId, String name, String msg, String date) {
        this.imgId = imgId;
        this.name = name;
        this.msg = msg;
        this.date = date;
    }


    public KakaoDTO(int imgId, String name, String msg) {
        this.imgId = imgId;
        this.name = name;
        this.msg = msg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
