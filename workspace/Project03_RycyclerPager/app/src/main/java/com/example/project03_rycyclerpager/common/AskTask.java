package com.example.project03_rycyclerpager.common;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AskTask extends AsyncTask<String, String, InputStream> {

    HttpClient httpClient; //접속을 위한 객체
    HttpPost httpPost; //Post 요청을 하기 위한 객체
    MultipartEntityBuilder builder;

    final String HTTPIP = "http://192.168.0.122"; //ipconfig(cmd에서 확인)
    final String SVRPAHT = "/02.Mid/";
    String mapping;
    private String postUrl;
    ArrayList<ParamDTO> params = new ArrayList<>();

    public AskTask(String mapping) {
        this.mapping = mapping;
    }
    public void addParam(String key, String value){
        params.add((new ParamDTO(key, value)));
    }

    @Override
    protected InputStream doInBackground(String... strings) {
        postUrl = HTTPIP + SVRPAHT + mapping; //url에 넣고 enter key 쳤을때 (요청 주소)
        builder = MultipartEntityBuilder.create(); //(외우지 않음) 빌더 초기화식(가져다씀)
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE); //
        for(int i=0; i<params.size(); i++) {
            builder.addTextBody(params.get(i).getKey(), params.get(i).getValue(), ContentType.create("Multipart/related", "UTF-8"));
        }

        httpClient = AndroidHttpClient.newInstance("Android"); //<=요청한 플랫폼(안드로이드 고정)
        httpPost = new HttpPost(postUrl); //만들어둔 Url을 넘겨주면 됨
        httpPost.setEntity(builder.build()); //요청
        InputStream in = null;
        try { //이부분
            in = httpClient.execute(httpPost).getEntity().getContent(); //실제 enter 치는 행위

        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }
}
