package com.example.connectionec;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.text.Editable;
import android.widget.EditText;

import com.google.gson.Gson;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

//반복이나 재사용 가능한 부분 찾기

//AsyncTask
//1. extends 상속이 필요함 <> 3번째가 중요함 = 리턴 타입 받을 타입
//parms은 dto나 list로 보내는게 높음
public class AskTask extends AsyncTask<String, String, InputStream> {



    HttpClient httpClient; //접속을 위한 객체
    HttpPost httpPost; //Post 요청을 하기 위한 객체
    MultipartEntityBuilder builder; //Web => Web (File 빼고는 MultiPart처리가 필요없음 같은 웹을 사용중이라)
    // http => http 프로토콜 같음(req, res)
    // Android, Iot, 기기(여러가지) => Web(WAS) 요청 Multipart 처리가 필요함 (그냥 받을 수 없음)
    // http => http 프로토콜의 바디 부분에 데이터를 써서 나눠서 보내는 형태
    //데이터 요청 url 멀티 파트는 브라우저 body 부분에 써서 보냄 멀티 파트를 요청 안하면 요청만 가능
    final String HTTPIP = "http://192.168.0.122"; //ipconfig(cmd에서 확인)
    final String SVRPAHT = "/01.Middl/";
    String mapping;
    private String postUrl;
    ArrayList<ParamDTO> params = new ArrayList<>();

    public AskTask(String mapping) {
        this.mapping = mapping;
    }

    public void addParam(String key, String value){
        params.add((new ParamDTO(key, value)));
    }

    // MainActivity activity;
    //ArrayList<String> params;

//    //매핑과 파라메터는 매번 바뀌어야함
//    public AskTask(String mapping, String paramdata) {
//        this.mapping = mapping;
//        this.paramdata = paramdata;
//    }
//
//    public AskTask(String mapping, ArrayList<String> params) {
//        this.mapping = mapping;
//        this.params = params;
//    }
//
//    //생성자 없으면 오류
//    public AskTask(String mapping, MainActivity activity) {
//        this.mapping = mapping;
//        this.activity = activity;
//    }
//
//    public AskTask(String mapping) {
//        this.mapping = mapping;
//    }


    @Override
    protected InputStream doInBackground(String... strings) {
        //작업이 사작되는 부분 - 시작후 디버깅 찍어서 봄
        postUrl = HTTPIP + SVRPAHT + mapping; //url에 넣고 enter key 쳤을때 (요청 주소)
        builder = MultipartEntityBuilder.create(); //(외우지 않음) 빌더 초기화식(가져다씀)
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE); //인터넷 형태로 리턴 -> 켜놓고 enter치는 형식
        //▲ 윗 부분까지는 고정으로 작성
        //나중에 파라메터를 추가하는 부분 계속 바뀜(나중에 추가할 것)
        //MultiPart (여러부분으로 나누어진) 에서 데이터를 보낼땐 String
        //=> json [] 리스트, {} 오브젝트
        //항ㅇ상 바뀜
        //params [0] : 검색조건 String search = "닉네임"
        //params [1] : LoginDTO { key : value }
        for(int i=0; i<params.size(); i++) {
            builder.addTextBody(params.get(i).getKey(), params.get(i).getValue(), ContentType.create("Multipart/related", "UTF-8"));
        }
        //builder.addTextBody("dto", paramdata, ContentType.create("Multipart/related", "UTF-8"));
//        for(int i = 0; i<params.size(); i++) {
//            builder.addTextBody("param" + i, params.get(i), ContentType.create("Multipart/related", "UTF-8"));
//        }
//        builder.addTextBody("size", params.size()+"", ContentType.create("Multipart/related", "UTF-8"));
//            builder.addTextBody("key", "value", ContentType.create("Multipart/related", "UTF-8"));
//            //key와 value만 알면 됨 ▲

        //파라메터를 입력받는 부분을 확장성있게 코딩을하면 계속해서 사용가능함

        //▼고정 된 부분 (절대 외우거나 깊게 공부할 필요가 없음 )
        //대충의 흐름을 이해하는 게 중요함 -> 깊게 이해할 필요 X
        httpClient = AndroidHttpClient.newInstance("Android"); //<=요청한 플랫폼(안드로이드 고정)
        httpPost = new HttpPost(postUrl); //만들어둔 Url을 넘겨주면 됨
        httpPost.setEntity(builder.build()); //요청할때 어떤 속성을 갖을지 ->빌더에 담아뒀음
        InputStream in = null;
        //execute는 try 처리
        try {
                in = httpClient.execute(httpPost).getEntity().getContent(); //실제 enter 치는 행위
           //inputStrea dto 처리
            //바뀌는 곳 ▼
//            Gson gson = new Gson();
//            MemberDTO dto = gson.fromJson(new InputStreamReader(in), MemberDTO.class); //리더를 넘겨줘야함
//            String aaa = "";
            // String aa = rtnString(in);
           // activity.changText(aa);
            //MainActivity.tv_data.setText(rtnString(in)); 비추천
            //rtnData = rtnString(in); <= 초기 Servlet(미들웨어)만들고 테스트용
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }//doin

    public String rtnString(InputStream in) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder strbrd = new StringBuilder();
        String line = null;
        while( (line = reader.readLine()) !=null) {
            strbrd.append( line ); //줄바꿈 제외 했음
        }

        return strbrd.toString();
    }

}
