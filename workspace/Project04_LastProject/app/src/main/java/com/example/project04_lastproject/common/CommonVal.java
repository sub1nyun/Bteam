package com.example.project04_lastproject.common;

import com.example.project04_lastproject.member.MemberVO;

public class CommonVal {
    //세션처럼 사용할것
    //public static MemberVO =
//    final String HTTP_IP = ""; //아이디 수정시 변경
//    final String SERVER_IP = "";
//    공통으로 사용할 정보(변수, 오브젝트 등)들을 모아놓는 클래스
    public static MemberVO loginInfo = null;
    //어떤 화면에서든 접근해서 loginInfo가 null이라면 로그인이 안 되어있다는것을 판단함

}
