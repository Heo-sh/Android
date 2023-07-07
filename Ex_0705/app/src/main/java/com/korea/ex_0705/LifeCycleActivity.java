package com.korea.ex_0705;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Process;
import android.util.Log;

public class LifeCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        //확인 하고 싶은 data를 눈으로 보고 싶다면 Log를 사용
        Log.i("MY", "--onCreate--");

    } //onCreate

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MY", "--onDestroy--");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MY", "--onPause--");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MY", "--onRestart--");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MY", "--onResume--");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MY", "--onStop--");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MY", "--onStart--");
    }
}