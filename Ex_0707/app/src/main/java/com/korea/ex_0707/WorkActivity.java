package com.korea.ex_0707;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WorkActivity extends AppCompatActivity {

    Button ok;

    TextView res;

    EditText text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        ok = findViewById(R.id.ok);
        res = findViewById(R.id.res);
        text = findViewById(R.id.text);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼을 눌렀을 때 EditText의 값 가져오기
                String ori = text.getText().toString();
                //원본 글을 잘라서 거꾸로 넣어줄 문자열 객체
                String str = "";

                //ori의 문자열을 하나씩 잘라서 str에 넣기
                for (int i = ori.length() - 1; i >= 0; i--) {
                    str += ori.charAt(i);
                }

                //ori와 str 내용이 같인지 비교해서 결과 출력
                if (ori.equals(str)) {
                    res.setText("회문입니다.");
                } else {
                    res.setText("회문이 아닙니다.");
                }

            }
        });
    }

}