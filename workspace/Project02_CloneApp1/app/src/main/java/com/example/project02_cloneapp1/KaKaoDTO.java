package com.example.project02_cloneapp1;

public class KaKaoDTO {
    private int ImageId;
    private String name, msg, date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public KaKaoDTO(int imageId, String name, String msg, String date) {
        this.ImageId = imageId;
        this.name = name;
        this.msg = msg;
        this.date = date;
    }

    public KaKaoDTO(int imageId, String name, String msg) {
        this.ImageId = imageId;
        this.name = name;
        this.msg = msg;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
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
