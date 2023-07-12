package com.korea.ex_0707;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class LotActivity extends AppCompatActivity {

    TextView res;

    Button one, two, three, four, again;

    //난수 저장용 변수
    int rnd = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lot);

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        again = findViewById(R.id.again);

        res = findViewById(R.id.res);

        //Button 공통 감지자
        one.setOnClickListener(click);
        two.setOnClickListener(click);
        three.setOnClickListener(click);
        four.setOnClickListener(click);

        //난수 생성 메서드
        setRandom();
        
        //again Button을 누르면 다시 난수를 생성 및, res의 text를 처음으로 돌리기
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                res.setText("result");
                setRandom();
            }
        });
    }

    //Button 1, 2, 3, 4가 사용할 이벤트 감지자
    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //클릭된 Button에 쓰여진 Text를 가져오는 것이 목적
            //getText()는 TextView, Button이나 EditText와 같은 '자식객체에서만' 사용 가능한 메서드
            //view와 같은 부모는 사용 불가
            
            //view 객체를 Button으로 형 변환
            int str = Integer.parseInt(((Button)view).getText().toString());

            if (str == rnd) {
                //Button에 쓰여진 Text와 랜덤값이 같다면 당첨
                res.setText("당첨!");
            } else {
                res.setText("꽝!");
            }
        }
    }; //click

    public void setRandom() {
        rnd = new Random().nextInt(4) + 1;
    }; //setRandom
}