package com.example.project04_lastproject.common;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class AskTask extends AsyncTask<String , String , InputStream> {
    HttpClient httpClient;
    HttpPost httpPost;
    MultipartEntityBuilder builder;
    final String HTTPIP = "http://192.168.0.122";
    final String SVRPATH = "/middle/";
    String mapping;
    private String postUrl;
    //String형태의 json구조를 가진 파라메터들을 추가할때사용, addTextBody
    ArrayList<ParamDTO> params = new ArrayList<>();
    //String으로 경로를 받아와서 File로 바꿔서 파라메터로 사용, addPart(File)
    ArrayList<ParamDTO> fileParams = new ArrayList<>();

    public AskTask(String mapping) {
        this.mapping = mapping;
    }


    public void addParam(String key, String value) {
        params.add(new ParamDTO(key, value));
    }

    //파일 보낼때 사용
    public void addFileParam(String key, String value) {
        fileParams.add(new ParamDTO(key, value));
    }

    @Override
    protected InputStream doInBackground(String... strings) {
        postUrl = HTTPIP + SVRPATH + mapping;//url에 넣고 enter key쳤을때 (요청,주소)
        builder = MultipartEntityBuilder.create();//빌더 초기화식.(가져다가쓰면됨)
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);//인터넷 켜놓고 엔터치는 형식

        for (int i = 0; i < params.size(); i++) {
            builder.addTextBody(params.get(i).getKey(), params.get(i).getValue()
                    , ContentType.create("Multipart/related", "UTF-8"));
        }
        //파일을 추가하는 로직
        for (int i = 0; i < fileParams.size(); i++) {               //파일 경로
            builder.addPart(fileParams.get(i).getKey(), new FileBody(new File(fileParams.get(i).getValue())));
        }
            httpClient = AndroidHttpClient.newInstance("Android");//<=요청한 플랫폼(Android고정)
            httpPost = new HttpPost(postUrl);
            httpPost.setEntity(builder.build());
            InputStream in = null;
            try {
                in = httpClient.execute(httpPost).getEntity().getContent();//실제 enter key <-
            } catch (IOException e) {
                e.printStackTrace();
            }


            return in;
        }//do


    }


