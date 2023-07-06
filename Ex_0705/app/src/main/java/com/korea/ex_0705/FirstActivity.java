package com.korea.ex_0705;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FirstActivity extends AppCompatActivity {

    @Override
    //java의 main 역할
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //내가 어떤 layout을 점유할 것이냐: layout은 xml로 구성
        //출력은 java, css는 xml
        //java class를 만들면 자동적으로 xml파일이 만들어진다.
        //img는 drawble 폴더에 icon은 mipmap 폴더에 넣는다.
        //icon은 해상도에 따라 넣어지는 mipmap 폴더 위치가 달라진다.
        //devicemanger에서 에뮬레이터도 version에 맞게끔 다운받아줘야 한다.
        setContentView(R.layout.activity_first);
    }
}