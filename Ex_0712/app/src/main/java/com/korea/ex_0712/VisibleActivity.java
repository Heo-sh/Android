package com.korea.ex_0712;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class VisibleActivity extends AppCompatActivity {

    Button back1, back2, back3, bot;

    ImageView img_gunship, img_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visible);

        //비행기 보이기
        back1 = findViewById(R.id.back1);
        //비행기 숨기기
        back2 = findViewById(R.id.back2);
        //bot Button toggle하기
        back3 = findViewById(R.id.back3);
        bot = findViewById(R.id.bot);

        img_gunship = findViewById(R.id.img_gunship);
        img_menu = findViewById(R.id.img_menu);

        back1.setOnClickListener(click);
        back2.setOnClickListener(click);
        back3.setOnClickListener(click);
    }

    //감지자로 event 처리하는 법 세가지
    //1. view == R.id btn -> id를 통해서 비교
    //2. view를 자식클래스로 캐스팅
    //3. 주소가 같기 때문에 이름으로 비교 가능
    
    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (back1 == view) {
                //비행기를 보여주고 동그라미를 숨긴다.
                img_gunship.setVisibility(View.VISIBLE);
                img_menu.setVisibility(View.INVISIBLE);
            } else if (back2 == view) {
                //동그라미를 보여주고 비행기를 숨긴다.
                img_menu.setVisibility(View.VISIBLE);
                img_gunship.setVisibility(View.INVISIBLE);
            } else if (back3 == view) {
                //getVisibility(): 현재 객체의 visible 속성을 가져온다.
                if (bot.getVisibility() != View.VISIBLE) {
                    bot.setVisibility(View.VISIBLE);
                } else {
                    bot.setVisibility(View.GONE);
                }
            }
        }
    };
}