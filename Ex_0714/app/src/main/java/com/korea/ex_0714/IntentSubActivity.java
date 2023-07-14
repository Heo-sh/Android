package com.korea.ex_0714;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class IntentSubActivity extends AppCompatActivity {

    TextView name2, age2, tel2, birth2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_sub);

        //main에서 넘어온 intent 객체
        Intent i = getIntent();

        //넘겨받은 intent로부터 저장된 값 추출
        String name = i.getStringExtra("name");
        String age = i.getStringExtra("age");
        String tel = i.getStringExtra("tel");
        String birth = i.getStringExtra("birth");

        name2 = findViewById(R.id.name2);
        age2 = findViewById(R.id.age2);
        tel2 = findViewById(R.id.tel2);
        birth2 = findViewById(R.id.birth2);

        name2.setText("이름: " + name);
        age2.setText("나이: " + age);
        tel2.setText("전화: " + tel);
        birth2.setText("생일: " + birth);

    }
}