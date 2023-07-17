package com.korea.ex_0717;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SharedPreferencesActivity extends AppCompatActivity {

    SharedPreferences pref;

    TextView value;
    Button plus, minus, reset;
    
    //value의 내용을 갱신하기 위한 변수
    int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        //같은 프로젝트 안에서라면
        //다른 액티비티에서 같은 이름을 가진 SP를 만들어서 같은 key 값을 가져오면
        //value값 공유 가능
        pref = getSharedPreferences("SHARE", MODE_PRIVATE);

        //앱을 최초로 실행을 했을 때 save라는 이름으로 저장된 key값이 없다
        n = pref.getInt("save", 0);
    
        value = findViewById(R.id.value);
        //문자열 공백에 int n을 넣는 방법
        //String.valueOf(n)과 같음
        value.setText("" + n);

        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        reset = findViewById(R.id.reset);

        plus.setOnClickListener(click);
        minus.setOnClickListener(click);
        reset.setOnClickListener(click);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();

            if (id == plus.getId()) {
                n++;
                value.setText(String.valueOf(n));
            } else if (id == minus.getId()) {
                n--;
                if (n < 0) {
                    Toast.makeText(SharedPreferencesActivity.this, "0 이하로는 내려가지 않습니다.", Toast.LENGTH_SHORT).show();
                    n = 0;
                }
                value.setText(String.valueOf(n));
            } else if (id == reset.getId()) {
                n = 0;
                value.setText(String.valueOf(n));
            }
        }
    };


    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor edit = pref.edit();

        edit.putInt("save", n);
        //물리적으로 savedata를 저장
        //앱의 registry 영역에 저장된다고 하지만,
        //파일탐색기로는 검색 불가
        //앱이 삭제될 때 같이 삭제되고, 다시 설치하면 값이 저장되어 있지 않다.
        edit.commit();
    }
}