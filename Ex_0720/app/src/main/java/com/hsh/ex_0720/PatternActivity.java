package com.hsh.ex_0720;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

public class PatternActivity extends AppCompatActivity {

    TextView password;

    PatternLockView lockView;

    //pattern 상태
    String restorePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern);

        lockView = findViewById(R.id.lockView);
        password = findViewById(R.id.password);

        lockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {
                Log.i("my", "Pattern 그리기 시작");
            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {
                Log.i("my", "Pattern 그리기 ing: " + PatternLockUtils.patternToString(lockView, progressPattern));
            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                Log.i("my", "Pattern 그리기 Complete: " + PatternLockUtils.patternToString(lockView, pattern));
                //입력한 Password
                String user_password = PatternLockUtils.patternToString(lockView, pattern);
                //1-1. 저장된 password가 존재하면
                if (!restorePassword.equals("")) {
                    //2-1. 입력된 Pattern과 저장된 Password가 같다면
                    if(user_password.equals(restorePassword)) {
                        password.setText("Success");
                        //Pattern color 변경
                        lockView.setCorrectStateColor(Color.parseColor("#0000FF"));
                    } else {
                        //2-2.입력된 Pattern과 저장된 Password가 다르다면
                        password.setText("Wrong Pattern");
                        lockView.setCorrectStateColor(Color.parseColor("#FF0000"));
                    }
                } else {
                    //1-2. 저장된 Password가 없다면
                    //현재 Password 저장
                    SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("password", user_password);
                    editor.commit();
                    password.setText("저장 완료");
                    //Pattern 지우기
                    lockView.clearPattern();
                }
            }

            @Override
            public void onCleared() {
                Log.i("my", "Pattern 그리기 Cleared");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        restorePassword();
    }

    public void restorePassword() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);

        //저장된 Password 변수에 담기
        restorePassword = pref.getString("password", "");

        //등록한 Password가 있을 때
        if (pref != null && !restorePassword.equals("")) {
            password.setText("등록한 패턴 입력");
        } else {
            //Password가 없을 때
            password.setText("저장된 패턴 없음");
        }
    }




















}