package com.example.project04_lastproject.member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project04_lastproject.MainActivity;
import com.example.project04_lastproject.R;
import com.example.project04_lastproject.common.AskTask;
import com.example.project04_lastproject.common.CommonMethod;
import com.example.project04_lastproject.common.CommonVal;
import com.google.gson.Gson;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.Account;
import com.kakao.sdk.user.model.Gender;
import com.kakao.sdk.user.model.Profile;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {

    EditText edt_id, edt_pw;
    Button btn_login, btn_join;
    Intent intent;
    CheckBox chk_auto;
    ImageView imgv_kakao;
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //카카오 지원 기능 사용하기 위한 키
        KakaoSdk.init(this, "c1bdc0068a9d840e4ad3de07a83bf4da");

        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        btn_login = findViewById(R.id.btn_login);
        chk_auto = findViewById(R.id.chk_auto);
        btn_join = findViewById(R.id.btn_join);

        imgv_kakao = findViewById(R.id.imgv_kakao);

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
      //  if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
      //      UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
        //토큰, 처리 ,유닛
        Function2<OAuthToken, Throwable, Unit> callBack = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                // 소셜 Token에 정보를 넣어서 바로 주는경우, <= O
                // Token을 통해서 특정 URL을 요청했을때 정보를 주는 경우
                if(throwable != null) { //먼저 오류가 있는지 확인
                    Toast.makeText(LoginActivity.this, "오류 발생"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }//토큰 이용해서 작업
                if(oAuthToken != null){
                    Toast.makeText(LoginActivity.this, "정보 잘받아옴", Toast.LENGTH_SHORT).show();
                    getKakaoInfo();

                }

                return null;
            }
        };
        imgv_kakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //자바에서는 getInstance
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)){
                    //카카오톡이 설치가 되어있는 핸드폰의 경우 카카오톡 어플을 통해서 인증하는 방법이 더 편리함
                    //카카오톡 특성상 로그인이 되어있고 2차인증을 하기만 하면 됨

                    //실제 로그인 시 콜백이 있어야함 -> 없으면 결과를 받아 올 수 없음
                    UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this, callBack );
                }else {
                    //카카오톡이 설치가 안 되어 있는 경우 -> Web을 통해서 (Redirect Uri) Activity? - 추가했음 처음에
                    UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this, callBack);
                }
            }
        });





        SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
        String id = preferences.getString("id", "--");
        String pw = preferences.getString("pw", "--");
        Boolean isLogin = preferences.getBoolean("autologin", false);
        chk_auto.setChecked(isLogin); //자동로그인을 체크하고나서 앱을 종료해도 그대로 저장된상태를 보여줌


        //getString String return -> String 변수에 담아줌
        //new - Interface 초기화
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edt_id.getText()+"";
                String pw = edt_pw.getText()+"";
                MemberDAO dao = new MemberDAO();

                if(dao.isLogin(id,pw)) {
                //기존 로그인 => (테스트용) id.equals("aaass") && pw.equals("bbbbss") ) {
                    saveLoginInfo(); //로그인 성공시 메소드 실행
                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this, "입력정보 확인하세요", Toast.LENGTH_SHORT).show();
                    edt_id.setText("");
                    edt_pw.setText("");
                }
            }
        });
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 추후 카메라 (Img WAS 전송 처리 부분)
                //Spring이 익숙해지고 나서 처리할 부분
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });

        //실제 온클릭처리
        if(isLogin) {
            edt_id.setText(id);
            edt_pw.setText(pw);
            btn_login.callOnClick(); //OnClick을 강제로 실행함.
        }

    }

    public void saveLoginInfo() { //로그인 성공시 저장할 것
        //체크박스 사용 방법 -> is true false 리턴
        //체크박스 자동로그인이 체크가 된 상태라면 임시 데이터를 저장함 (로그인 정보를 )
        try {//ㅈㅓ장처리
            SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            if(chk_auto.isChecked()) { //체크 -> 로그인 정보를 저장함
                //preferences =  //로그인 정보의 경우는 PRIVATE 사용
                //초기에 우리가 공유자원에 어떤 데이터를 key로 지정을 해서 넣어놓지 않은 상태
                //데이터가 존재하는지 먼저 확인
                 //Nasted Class 중첩클래스 new 초기화 아닌 만들어진 메소드로 사용함
                 editor.putBoolean("autologin", true);
                editor.putString("id", edt_id.getText()+ ""); //String 값 -> putString -> get으로 가져옴
                editor.putString("pw", edt_pw.getText()+ "");
                //editor.apply(); // 저장 확정 -> 파라매터 추가시 오류가 난다면 추가 하지 않기위해서 존재함
            }else { // 비활성 -> 로그인 정보를 삭제함
                //저장 정보 삭제 -> editor 필요
//                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("autologin");
                editor.remove("id");
                editor.remove("pw");
                //editor.putString("id", null);
                //editor.clear();
                //editor.apply();
            }
            editor.apply();
        }catch (Exception e) {
            Toast.makeText(LoginActivity.this, "자동로그인 정보 저장 실패", Toast.LENGTH_SHORT).show();

        }

    }

    //카카오 로그인이 성공을 해도 내가 만든 앱의 사용자인지는 판단을 해야함
    //=> select 정보 where id=>email 일치하는지 => 없다면 회원가입 페이지로 이동시키기.
    public void getKakaoInfo() {    //람다식
        UserApiClient.getInstance().me((user, throwable) -> {
        if(throwable != null) {//오류 담아져 있는지 확인
            //오류임. 정보 못 받아옴 (Token이 없거나 Token을 삭제했을때 (logout) -> 오류로그를 줌
            //KOE + 숫자
            Toast.makeText(LoginActivity.this, "오류 코드 :" +throwable.getMessage(), Toast.LENGTH_SHORT).show();
        }else {//정보를 받아왔다면
            //카카오에 있음 [ { } ] json 구조 처럼 바로 데이터가 있는게 아님
            //Account라는 Object안에 List가 있거나 List안에 OBject가 있는형식
            Account kakaoAcount = user.getKakaoAccount();
            if(kakaoAcount != null) {//받아온 정보가 있다면
                String email = kakaoAcount.getEmail();
                Profile profile = kakaoAcount.getProfile();
                String nickname = profile.getNickname();



                //Ask 이용 회원 조회
                AskTask kakaotask = new AskTask("kakaoLogin");
                        kakaotask.addParam("id",email );

                     InputStream in =  CommonMethod.executeGet(kakaotask);
                MemberVO vo = gson.fromJson(new InputStreamReader(in), MemberVO.class);
                if(vo != null) {
                    CommonVal.loginInfo = vo;
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                    intent.putExtra("email", email);

                    startActivity(intent);

                }

            }
        }
                return null;
            });
    }
}