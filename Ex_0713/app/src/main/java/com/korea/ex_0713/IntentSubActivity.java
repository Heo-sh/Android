package com.korea.ex_0713;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntentSubActivity extends AppCompatActivity {

    Button prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_sub);

        prev = findViewById(R.id.prev);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(IntentSubActivity.this, IntentActivity.class);

                //Flag의 활용으로 Activity가 겹치는 문제를 해결 가능하다.
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                startActivity(i);

                //Activity 전환은 각각 Activity가 쌓이는 상태로 전환이 되는 것이다.
                //사용자 입장에선 불편함
                //그래서 finish를 써주는데 화면이 적으면 괜찮지만 많아지면
                //Intent Flag를 써주자

                //현재 액티비티 종료
                //finish();
            }
        });
    }
}