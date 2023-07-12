package com.korea.ex_0707;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EventActivity extends AppCompatActivity {

    Button red, green, blue, send, reset;
    TextView res;
    EditText txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        //findViewById(): Event 처리에 사용할 객체를 검색
        //R: 안드로이드에서의 최상위 객체 --> public final class com.korea.ex_0707.R
        red = findViewById(R.id.red); //Button Red
        green = findViewById(R.id.green); //Button green
        blue = findViewById(R.id.blue); //Button blue
        send = findViewById(R.id.send); //Button Send
        reset = findViewById(R.id.reset); //Button Reset

        txt = findViewById(R.id.txt); //EditText txt

        //공통 감지자
        send.setOnClickListener(click); //Button send
        reset.setOnClickListener(click); //Button reset

        res = findViewById(R.id.res); //TextView

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //button click 시 호출되는 영역
                red.setBackgroundColor(Color.parseColor("#ff0000"));
                res.setBackgroundColor(Color.parseColor("#ff0000"));
                res.setText("빨강");
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                green.setBackgroundColor(Color.parseColor("#00ff00"));
                res.setBackgroundColor(Color.parseColor("#00ff00"));
                res.setText("초록");
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blue.setBackgroundColor(Color.parseColor("#0000ff"));
                res.setBackgroundColor(Color.parseColor("#0000ff"));
                res.setText("파랑");
            }
        });
    } //onCreate

    //공통감지자 기능 실현
    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.send) {
                String str = txt.getText().toString(); //반환형이 다 int이기에 toString()을 해줘야 한다.\
                res.setText(str);
            } else if (id == R.id.reset) {
                res.setBackgroundColor(Color.BLACK);
                res.setText("결과");
            }
        } //onClick
    };
}