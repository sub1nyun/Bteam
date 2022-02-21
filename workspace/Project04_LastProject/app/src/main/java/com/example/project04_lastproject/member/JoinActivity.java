package com.example.project04_lastproject.member;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.project04_lastproject.R;
import com.example.project04_lastproject.common.AskTask;
import com.example.project04_lastproject.common.CommonMethod;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JoinActivity extends AppCompatActivity {

    ImageView img_profile;
    EditText edt_id, edt_pw, edt_name;
    RadioButton rdo_man, rdo_woman;
    RadioGroup rdo_grp; // 버튼을 따로 따로 사용을 하면 메소드나 if문 공부(알고리즘)가 되나 불편함
    Button btn_join, btn_cancel;
    Gson gson = new Gson();
    MemberVO vo = new MemberVO(); //비어있는 상태의 vo를 하나 생성
    //EditText 하나로 묶음
    //스피너
    String[] spn_item = {"카메라", "갤러리"};
    EditText[] edt_arr = new EditText[3]; //초->중 어떤 타입의 클래스든 대부분은 배열, List 자료구조형으로 만들 수 있음
    ArrayList<EditText> list = new ArrayList<>();   //이대로 두면 초기값은 모두 Null

    public final int CAMERA_CODE = 1004; // 카메라 액티비티 실행 (암시,묵시) 제어권 x
    public final int GELLARY_CODE = 1005; // 갤러리 액티비티 실행 (암시,묵시) 제어권 x

    File imgFile = null;
    String imgFilePath = null;
    //카메라 -> 갤러리 -> 이미 있기때문에 경로 실행
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        checkDangerousPermissions();
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        img_profile = findViewById(R.id.img_profile);
        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        edt_name = findViewById(R.id.edt_name);
        rdo_grp = findViewById(R.id.rdo_grp);
//        edt_arr[0] = findViewById(R.id.edt_id);
//        edt_arr[1] = findViewById(R.id.edt_name);
//        edt_arr[2] = findViewById(R.id.edt_pw);
        rdo_man = findViewById(R.id.rdo_man);
        rdo_woman = findViewById(R.id.rdo_woman);
        btn_join = findViewById(R.id.btn_join);
        btn_cancel = findViewById(R.id.btn_cancel);

        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //[ Edittext ] [ Edittext ] [ Edittext ].
//                for(int i=0; i<edt_arr.length; i++) {
//                    Toast.makeText(JoinActivity.this, edt_arr[i].getText()+"", Toast.LENGTH_SHORT).show();
//                }
                if(edt_id.getText().length() == 0 && edt_pw.getText().length() ==0 && edt_name.getText().length() ==0) {
                    Toast.makeText(JoinActivity.this, "데이터를 전부 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                    vo.setId(edt_id.getText()+"");
                    vo.setPw(edt_pw.getText()+"");
                    vo.setName(edt_name.getText()+"");
                    AskTask task = new AskTask("join");
                    Gson gson = new Gson();
                    task.addParam("vo", gson.toJson(vo));
                    CommonMethod.executeGet(task);

                    finish();
            }
        });



        rdo_grp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(R.id.rdo_man == checkedId) {
                    //dto에 남자라는 값을 담기
                    vo.setGender("남");
                }else {
                    vo.setGender("여");
                }
            }
        });


        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AskTask task = new AskTask("kakaoJoin");
                MemberVO vo = new MemberVO();
                vo.setId(edt_id.getText()+"");
                vo.setPw(edt_pw.getText()+"");
                vo.setName(edt_name.getText()+"");
                vo.setGender(rdo_woman.isChecked() ? "여" : "남");
                task.addParam("vo", gson.toJson(vo));
                if(imgFilePath != null) {//사진 업로드 안해도 가입
                    task.addFileParam("file", imgFilePath);
                }
                CommonMethod.executeGet(task);

                finish();
                 //MemberVO test = gson.fromJson(new InputStreamReader(in), MemberVO.class);
            }
        });




        if (email != null) {
            edt_id.setText(email);
            edt_id.setEnabled(false);
        }
    }//class

    public void go_gallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GELLARY_CODE); //사진을 고르게 넘겨준 후 갤러리 코드를 넘겨줌
    }


    public void     go_Camera() {
        //명시적이 아닌 -> 암시, 묵시적 인텐트 실행 => Camera기능을 호출
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //사진을 찍어왔을때 가지고 올 수 있는 인텐트
        //인텐트가 사용이 가능한지 (MediaStore 기능이 사용가능한지 체크 -> 카메라가 있는지, 사용이 불가한 엑티비티인지 등)
        if(intent.resolveActivity(getPackageManager()) != null) { //사용가능한지 -> 대부분 널이 아닐 수 없음(카메라가 다 달려있음 폰에)
            //이미지 파일을 만들고 저장하는 처리가 필요함
            imgFile = null; //여러번 사용 함 널로 비워둠
            imgFile =createFile(); //임시파일 불러옴

            //어디를 붙혀넣어야 하는지 알아야함 외우기 ㄴㄴ

            if(imgFile != null) { //jpg형식으로 잘 만들어져서 널이 아니면
                //API 24이상부터는 FileProvider를 제공해야함
                //Context <=
                Uri imgUri = FileProvider.getUriForFile(
                        getApplicationContext(), //인증까지가능 -> 일반 context(액티) 넘겨주면 기능이 빠진 context라 오류
                        getApplicationContext().getPackageName()+".fileprovider", //실제 파일의 경로까지 인증 후 실제 파일에 넣어줌
                        imgFile
                );
                //API 23버전 부터는 Provider를 넣으면 오류
                //버전 분기를 위한 처리
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){ //N =24
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri); //imgUri를 통해서 카메라로 찍은사진을 전달 받음
                }else {//프로바이더 사용이 안되면
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imgFile)); //인증을 받았기때문에 바로 사용?
                }
                startActivityForResult(intent,CAMERA_CODE);

            }
        }
    }

    //알아오는 메소드
    public String getGalleryRealPath(Uri contentUri) { //가짜 uri를 주는데 이용힘
        String rtnPath = null;
        String[] paths = {MediaStore.Images.Media.DATA}; //<=
        Cursor cursor = getContentResolver().query(contentUri, paths, null, null, null); //가짜 uri와 paths
        //커서가 0번째 있다가 이동하면서 받아오면 스트링으로 빼올 수 있음
        if(cursor.moveToFirst()) { //데이터가 담겨있다
            int columns_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            rtnPath =cursor.getString(columns_index);
        }
        cursor.close();
        return  rtnPath;
    }//디버깅 찍어보고 어떤 것이 넘겨오는지 보기 -> 방법이 계속 바뀌기 때문에 공부 추천 ㄴㄴ




    // requestCode <= 여러가지 액티비티를 실행하고 (하나의 액티비티) 결과를 하나의 메소드에서 처리하기때문에
    // 어떤 액티비티가 끝났는지를 구별해야함. ( CameraActivity 1004(구별 숫자), GalleryActivity 1005.... 등)
    // 카메라로 사진을 찍게 되면 임시파일을 우리가 만들고 그 파일에 실제 사진을 넣고 파일은 임시파일이 아니 상태가 됨
    // 갤러리 <= 이미 있는 사진을 넘겨 받아야함 ( intent <= Null이 아닌 상태) 이미 있는 파일을 넘겨 받으면 됨
    //중요한 부분
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //Intent로 다시 사진파일을 넘겨줌
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_CODE && resultCode == RESULT_OK) { //카메라 코드와 같으면 끝난것
            Toast.makeText(JoinActivity.this, "사진을 잘찍었음", Toast.LENGTH_SHORT).show();
            Glide.with(JoinActivity.this).load(imgFilePath).into(img_profile);

        }else if(requestCode == GELLARY_CODE && resultCode == RESULT_OK) {
            Toast.makeText(JoinActivity.this, "갤러리 사진을 가져옴", Toast.LENGTH_SHORT).show();
            //getContentResolver.query <= 경로를 받아오는 처리 (다른 방법도 많음)
            //실제 저장경로 Uri를 알아옴
            Uri selectImageUri = data.getData();
            imgFilePath = getGalleryRealPath(selectImageUri);
            Glide.with(JoinActivity.this).load(imgFilePath).into(img_profile);
        }
    }


    //파일 생성을 위한 처리
    public File createFile() {
        //파일 이름이 중복되거나 하는것을 방지하기 위한 처리.
        String timeTemp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()); //중복 안 되게 날짜 시분초로 현재 날을 받아와봄
        String imageFileName = "My" + timeTemp;
        //이름만들기 끝 -> 경로를 만들기 시작 하기
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES); //경로 생성을 위한 처리
        File rtnFile =null;
        //파일 생성
        //임시 파일이 생성이 됨 (이미지파일명, 형식, 스토리지)
        try {
            rtnFile = File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgFilePath = rtnFile.getAbsolutePath();
        return rtnFile;
    }


    public void showDialog() {
        //context (this, JoinActivity.this )
        //  현재위치가 Activity.class 바디 부분인지
        //  ▲ 인터페이스나 익명의 객체의 바디부분{}에서 작업하는 경우
//        findViewById(R.id.rdo_grp).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(this., "", Toast.LENGTH_SHORT).show();
//            }//바디 부분안의 this는 인터페이스인  OnClickListener() 가르킴
//        });
//        this.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//선택된게 없으면 기본 -1(배열엔 -1 x 초기값), 클릭이 됐을때 인터페이스로 넘겨줌
        builder.setTitle("업로드 방법 선택").setSingleChoiceItems(spn_item, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int index) { //int which에서 수정
                if(spn_item[index].equals("카메라")){
                    //카메라를 이동하는 작업 (Intent <= ); 명시, 암시
                    Toast.makeText(JoinActivity.this, "카메라로 이동", Toast.LENGTH_SHORT).show();
                    go_Camera();
                }else {
                    //갤러리로 이동하는 작업
                    Toast.makeText(JoinActivity.this, "갤러리로 이동", Toast.LENGTH_SHORT).show();
                    go_gallery();
                }
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    // 위험권한
    private void checkDangerousPermissions() {
        String[] permissions = { //요청할것을 따로 써둠
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_MEDIA_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
                                        //요청이 됐다는 의미
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else { //요청하는 부분
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }




}