package com.hsh.ex_0719;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NumberActivity extends AppCompatActivity {

    TextView show, question;

    LinearLayout linear;

    Button yes, no, restart;

    //결과를 출력할 변수
    int result = 0;

    //현재 스테이지
    int phase = 1;

//    final boolean;

    final int YES = 1;
    final int NO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        show = findViewById(R.id.show);
        question = findViewById(R.id.question);
        linear = findViewById(R.id.linear);
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        restart = findViewById(R.id.restart);

        yes.setOnClickListener(click);
        no.setOnClickListener(click);
        restart.setOnClickListener(click);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            
            if (id == yes.getId()) {
                //result에 값을 누적
                //다음 phase 문제 출제
                showPhase(YES);
            } else if (id == no.getId()) {
                //다음 phase 문제 출제
                showPhase(NO);
            } else if (id == restart.getId()) {
                yes.setVisibility(View.VISIBLE);
                no.setVisibility(View.VISIBLE);
                question.setVisibility(View.VISIBLE);
                restart.setVisibility(View.GONE);

                show.setText("04 05 06 07 12 \n13 14 15 20 21 \n22 23 28 29 30");
                phase = 1;
                result = 0;
            }
        }
    };

    public void showPhase(int select) {
        String str = "";

        switch (phase) {
            case 1:
                if (select == YES) {
                    result += 4;
                }
                //다음 스테이지 문제
                str = "16 17 18 19 20\n21 22 23 24 25\n26 27 28 29 30";
                break;
            case 2:
                if (select == YES) {
                    result += 16;
                }
                //다음 스테이지 문제
                str = "01 03 05 07 09\n11 13 15 17 19\n21 23 25 27 29";
                break;
            case 3:
                if (select == YES) {
                    result += 1;
                }
                //다음 스테이지 문제
                str = "08 09 10 11 12 \n13 14 15 24 25 \n26 27 28 29 30";
                break;
            case 4:
                if (select == YES) {
                    result += 8;
                }
                //다음 스테이지 문제
                str = "02 03 06 07 10\n11 14 15 18 19\n22 23 26 27 30";
                break;
            case 5:
                if (select == YES) {
                    result += 2;
                }
                //결과가 0이거나 31일 경우
                if (result == 0 || result == 31) {
                    str = "잘못 선택한 문항이 있습니다.";
                } else {
                    str = "당신이 생각한 숫자는\n\""+ result +"\"" + "\n" + "입니다.";
                }
                yes.setVisibility(View.GONE);
                no.setVisibility(View.GONE);
                restart.setVisibility(View.VISIBLE);
                question.setVisibility(View.GONE);

                break;
        }

        show.setText(str);
        phase ++;
    }




















}