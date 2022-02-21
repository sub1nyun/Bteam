package com.example.project04_lastproject.notice;

import java.util.ArrayList;

public class GroupDTO {
    //처음 사용은 헷갈림
    //리스트 뷰를 세로로 뿌림 (하나당 리스트뷰 - 자식요소의 수가 다름)
    //부모 리스트뷰가 뿌려지고 자식 리스트뷰를 갖음

    //부모가 보여질 것
    private  String title, content;
    //자식의 요소
    private ArrayList<SubDTO> subList;


    public GroupDTO(String title, String content, ArrayList<SubDTO> subList) {
        this.title = title;
        this.content = content;
        this.subList = subList;
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

    public ArrayList<SubDTO> getSubList() {
        return subList;
    }

    public void setSubList(ArrayList<SubDTO> subList) {
        this.subList = subList;
    }
}
