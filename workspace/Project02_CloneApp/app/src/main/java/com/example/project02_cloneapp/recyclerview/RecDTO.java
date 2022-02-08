package com.example.project02_cloneapp.recyclerview;

public class RecDTO {
    private int resId;  //이미지
    private String textStr; //텍스트

    //생성자와 getter setter 생성
    public RecDTO(int resId, String textStr) {
        this.resId = resId;
        this.textStr = textStr;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTextStr() {
        return textStr;
    }

    public void setTextStr(String textStr) {
        this.textStr = textStr;
    }
}
