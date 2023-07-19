package com.hsh.ex_0719;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SwipeRefreshActivity extends AppCompatActivity {

    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);

        swipe = findViewById(R.id.swipe);

        //disk의 배경 color 변경
        swipe.setProgressBackgroundColorSchemeColor(Color.parseColor("#aaaaff"));

        //disk의 size 변경(DEFAULT: 기본값)
        swipe.setSize(SwipeRefreshLayout.LARGE);

        //loding의 color 변경
        swipe.setColorSchemeResources(R.color.violet, R.color.orange, R.color.yellow, R.color.green);

        //disk의 size를 점진적 증가시켜주기            //당겨지는 위치
        swipe.setProgressViewEndTarget(true, 300);

        swipe.setOnRefreshListener(swipeListener);

        //서버 통신 시 asynctask
        //일반 백그라운드 시 handler
    }

    SwipeRefreshLayout.OnRefreshListener swipeListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
        //당겼다가 손을 떼는 순간 호출되는 메서드
        //만약 서버랑 실제로 통신을 한다면
        //new Async().excute()
        //지금은 서버 통신을 하는 게 아니기에 handler로 처리한다.
            handler.sendEmptyMessageDelayed(0, 3000);
        }
    };

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //로딩이 완료된 시점에 디스크 제거
            swipe.setRefreshing(false);
        }
    };
}