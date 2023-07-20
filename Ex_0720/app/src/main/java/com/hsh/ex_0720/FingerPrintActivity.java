package com.hsh.ex_0720;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ajalt.reprint.core.AuthenticationFailureReason;
import com.github.ajalt.reprint.core.AuthenticationListener;
import com.github.ajalt.reprint.core.Reprint;

public class FingerPrintActivity extends AppCompatActivity {

    TextView text;

    ImageView finger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);

        text = findViewById(R.id.text);
        finger = findViewById(R.id.finger);

        //1. 지문사용을 위한 초기화
        Reprint.initialize(FingerPrintActivity.this);

        if (checkSpec()) {
            //지문 인식 가능 경우
            Reprint.authenticate(new AuthenticationListener() {
                @Override
                public void onSuccess(int moduleTag) {
                    //보통은 텍스트만 변환하지 않고 화면을 전환한다.
                    text.setText("인증 성공");
                }

                @Override
                public void onFailure(AuthenticationFailureReason failureReason, boolean fatal, CharSequence errorMessage, int moduleTag, int errorCode) {
                    text.setText("인증 실패\n다시 시도");
                }
            });
        }
    }

    //2. 지문인식이 가능한 지 판단(휴대폰의 스펙 확인)
    public boolean checkSpec() {
        //참이면 지문인식 센서가 존재한다는 것
        boolean hardware = Reprint.isHardwarePresent();

        //센서가 있어도 지문이 등록이 안되어 있으면 사용 불가
        //등록이 되어 있는지 확인
        boolean register = Reprint.hasFingerprintRegistered();

        if (!hardware) {
            Toast.makeText(FingerPrintActivity.this, "지문인식 센서가 없음.", Toast.LENGTH_SHORT).show();
        } else {
            if (!register) {
                Toast.makeText(FingerPrintActivity.this, "등록된 지문 없음\n지문등록 먼저 하시오", Toast.LENGTH_SHORT).show();
            }
        }

        return hardware && register;
    }



















}