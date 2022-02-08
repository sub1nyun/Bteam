package com.example.testproject02;

import java.io.Serializable;

public class LastDTO implements Serializable {
    private String ltv1, ltv2;

    public LastDTO(String ltv1, String ltv2) {
        this.ltv1 = ltv1;
        this.ltv2 = ltv2;
    }

    public String getLtv1() {
        return ltv1;
    }

    public void setLtv1(String ltv1) {
        this.ltv1 = ltv1;
    }

    public String getLtv2() {
        return ltv2;
    }

    public void setLtv2(String ltv2) {
        this.ltv2 = ltv2;
    }
}
