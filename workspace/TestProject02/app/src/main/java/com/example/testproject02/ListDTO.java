package com.example.testproject02;

public class ListDTO {
    private  int img1, img2;
    private  String tv1, tv2;

    public ListDTO(int img1, int img2, String tv1, String tv2) {
        this.img1 = img1;
        this.img2 = img2;
        this.tv1 = tv1;
        this.tv2 = tv2;
    }

    public int getImg1() {
        return img1;
    }

    public void setImg1(int img1) {
        this.img1 = img1;
    }

    public int getImg2() {
        return img2;
    }

    public void setImg2(int img2) {
        this.img2 = img2;
    }

    public String getTv1() {
        return tv1;
    }

    public void setTv1(String tv1) {
        this.tv1 = tv1;
    }

    public String getTv2() {
        return tv2;
    }

    public void setTv2(String tv2) {
        this.tv2 = tv2;
    }
}
