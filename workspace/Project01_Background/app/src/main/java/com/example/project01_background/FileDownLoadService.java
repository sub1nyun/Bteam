package com.example.project01_background;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class FileDownLoadService extends Service {

    private static final String TAG = "filedown:";
    //:을 넣어줘야함

    //필터링을 filedown으로 주고  주면 찍히는 것은 확인 가능
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    public FileDownLoadService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Log.d(TAG, "onStartCommand: 까지 옴");
        String value = intent.getStringExtra("key");
        //int progress = 1000;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "onStartCommand: " + value);
                for (int i =1; i<= 100; i++) {
                    Log.d(TAG, "onStartCommand: " + i + "%진행");
                    MainActivity.pb_bar.setProgress(i);
                    try {
                        Thread.sleep(1000); //<- Thread 서비스에 있는 쓰레드? Main?
                    } catch (InterruptedException e) {  //따로 만들지 않으면 Main 쓰레드이고
                        e.printStackTrace();            //비동기 처리하기 위해서 쓰레드 따로 생성

                    }
                }
            }
        });
        thread.start();




        return super.onStartCommand(intent, flags, startId);
    }

    //iBinder <-를 이용해서 화면이 바뀌거나 하는 처리들(ForeGround 보이는 화면 -정면)
    //을 넣었으나 에러 발생률이 높고 빈번해서 많이 사용은 하지 않음 ▼
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}