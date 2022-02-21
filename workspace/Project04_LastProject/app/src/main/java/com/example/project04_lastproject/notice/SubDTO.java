package com.example.project04_lastproject.notice;

public class SubDTO {

    private  String title, content;
    //자료구조를 따로 받을 필요가 없음

    public SubDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
