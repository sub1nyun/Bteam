package com.example.testproject02;

import java.io.Serializable;

public class userDTO implements Serializable {
    String id, pw, name, birth;

    public userDTO(String id, String pw, String name, String birth) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.birth = birth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
