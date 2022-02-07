package com.example.connectionec;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

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


public class MainAskTask extends AsyncTask<String, String, InputStream> {
    HttpClient httpClient; //접속을 위한 객체
    HttpPost httpPost; //Post 요청을 하기 위한 객체
    MultipartEntityBuilder builder; //Web => Web (File 빼고는 MultiPart처리가 필요없음 같은 웹을 사용중이라)
    final String HTTPIP = "http://192.168.0.122"; //ipconfig(cmd에서 확인)
    final String SVRPAHT = "/01.Middl/";
    String mapping;
    private String postUrl;
    ArrayList<ParamDTO> params = new ArrayList<>();

    public MainAskTask(String mapping) {
        this.mapping = mapping;
    }

    public void addParam(String key, String value){
        params.add((new ParamDTO(key, value)));
    }




    @Override //이부분
    protected InputStream doInBackground(String... strings) {
        //작업이 사작되는 부분 - 시작후 디버깅 찍어서 봄
        postUrl = HTTPIP + SVRPAHT + mapping;
        builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        //파라메터 추가 부분
        for(int i=0; i<params.size(); i++) {
            builder.addTextBody(params.get(i).getKey(), params.get(i).getValue(), ContentType.create("Multipart/related", "UTF-8"));
        }



        httpClient = AndroidHttpClient.newInstance("Android"); //<=요청한 플랫폼(안드로이드 고정)
        httpPost = new HttpPost(postUrl); //만들어둔 Url을 넘겨주면 됨
        httpPost.setEntity(builder.build()); //요청할때 어떤 속성을 갖을지 ->빌더에 담아뒀음
        InputStream in = null;
        //execute는 try 처리
        try { //이부분
                in = httpClient.execute(httpPost).getEntity().getContent(); //실제 enter 치는 행위

        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }//doin



}
