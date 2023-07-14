package com.korea.ex_0714;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class IntentMainActivity extends AppCompatActivity {

    EditText name, age, tel, birth;
    Button date, send;

    Dialog dialog;

    int count = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_main);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        tel = findViewById(R.id.tel);
        birth = findViewById(R.id.birth);

        date = findViewById(R.id.date);
        send = findViewById(R.id.send);

        send.setOnClickListener(click);
        date.setOnClickListener(d);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name1 = name.getText().toString();
            //유효성 검사
            if (name1.trim().length() == 0 ) {
                Toast.makeText(IntentMainActivity.this, "이름을 입력", Toast.LENGTH_SHORT).show();
                return;
            }

            String age1 = age.getText().toString();
            if (age1.trim().length() == 0 ) {
                Toast.makeText(IntentMainActivity.this, "이름을 입력", Toast.LENGTH_SHORT).show();
                return;
            }

            String tel1 = tel.getText().toString();
            if (tel1.trim().length() == 0 ) {
                Toast.makeText(IntentMainActivity.this, "이름을 입력", Toast.LENGTH_SHORT).show();
                return;
            }
            String birth1 = birth.getText().toString();

            //Intent를 통해 넘긴다.
            Intent i = new Intent(IntentMainActivity.this, IntentSubActivity.class);

            //값 전달을 위해 Intent 객체에 저장
            i.putExtra("name", name1);
            i.putExtra("age", age1);
            i.putExtra("tel", tel1);
            i.putExtra("birth", birth1);

            //화면 전환
            startActivity(i);
        }
    };

    View.OnClickListener d = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //java.util
            //달력에 최초로 표기될 오늘 날짜를 구한다.
            Calendar now = Calendar.getInstance();

            int y = now.get(Calendar.YEAR); //연
            int m = now.get(Calendar.MONTH); //월
            int d = now.get(Calendar.DAY_OF_MONTH); //일

            dialog = new DatePickerDialog(IntentMainActivity.this, dateSetListener, y, m, d);

            dialog.show();
        }
    };

    //달력의 변경사항을 감지하는 이벤트 감지자
    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        //i == 연, i1 == 월, i2 == 일
        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
            String res = String.format("%d-%02d-%02d", y, m+1, d);
            birth.setText(res);
        }
    };

    //Handler 실습
    //앱을 하나 켜고 뒤로가기 눌렀을 때 '뒤로가기를 한번 더 눌러야 종료됩니다.' 하고
    //토스트를 띄우고 떳을 때 3초 이내에 다시 누르면 종료, 3초가 지나면 두 번 누른 걸로
    //인정하지 않아서 토스트 다시 띄우기

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            handler.sendEmptyMessageDelayed(0, 1000);
            //2초 카운팅을 위한 핸들러
            if (count > 0) {
                --count;
            } else {
                count = 2;
                handler.removeMessages(0);
            }
        }
    };

    @Override
    public void onBackPressed() {
        Toast.makeText(IntentMainActivity.this, "뒤로 가기를 한 번 더 눌러야 종료됩니다.", Toast.LENGTH_SHORT).show();
        if (count != 2) {
            finish();
        } else {
            //핸들러 호출
            handler.sendEmptyMessage(0);
        }
    }
}
