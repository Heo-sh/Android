package com.korea.ex_0714;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HandlerWhatActivity extends AppCompatActivity {

    Button zero, one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_what);

        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);

        zero.setOnClickListener(click);
        one.setOnClickListener(click);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();

            //what은 같은 handler에서 각각의 기능이 다를 때,
            //구분해주는 하나의 식별자 같은 역할
            if (id == zero.getId()) {
                handler.sendEmptyMessage(0);
            } else if (id == one.getId()) {
                handler.sendEmptyMessage(1);
            }
        }
    };

    Handler handler = new Handler() {
        @Override
                                    //msg가 what date를 가지고 있다
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 0) {
                Toast.makeText(HandlerWhatActivity.this, "0으로 호출", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 1) {
                Toast.makeText(HandlerWhatActivity.this, "1로 호출", Toast.LENGTH_SHORT).show();
            }
        }
    };


}