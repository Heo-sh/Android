package com.korea.ex_0714;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HandlerActivity extends AppCompatActivity {

    TextView count;

    Button start, stop;

    int set_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        count = findViewById(R.id.count);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);

        start.setOnClickListener(click);
        stop.setOnClickListener(click);
    }

    //이벤트 감지자를 밖에다 만드는 이유, 안에다 만들면 더 위에있는 버튼들이 참조를 못하기 때문
    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();

            if (id == start.getId()) {
                //Background에서 count의 값을 1씩 증가시키는 Handler 호출

                //직접호출 시 일반메서드처럼 작동을 한다.
                //Background에서는 작동을 하지 안 한다.
                //handler.handleMessage();

                //handler의 handleMessage 호출 방법
                handler.sendEmptyMessage(0);

                //start를 한 번 누른 이후엔 비활성화되게 하는 코드
                //아래의 코드가 없으면 start 버튼을 계속 누르게 되면 시간에 상관없이
                //count가 증가되어버리기에 막아주는 것이다.
                start.setEnabled(false);

                //Android에서 while문은 위험하다. 하나의 코드를 실행 시
                //다른 작업들이 아예 안될 수 도 있다.
            } else if (id == stop.getId()) {
                //Handler 정지
                //handler의 Message를 지워주고
                //start를 다시 활성화 시켜주는 것
                handler.removeMessages(0);

                start.setEnabled(true);
            }
            //reset 버튼을 만들어 줄 수도 있으니 생각해보자.
        }
    };
    
    //Handler 준비
    //android.os
    //duplicate 앞으로 업데이트의 가능성은 없으니까 이걸 대체할 수 있는 다른데 생겼을 수 있거나
    //아니면 다른걸 조합해서 이걸 대체할 수 있는지 찾아보세요 라는 것.
    //줄 지우기 : alt + enter -> inspection -> Disable inspection
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //이렇게 할 경우, 앱의 속도가 빠르기에 본인을 다시 실행하는데 1초에 1000번씩 돌아버린다.
            //handler.sendEmptyMessage();

            //Background에서 code를 실행하는 영역
                                            //무엇을 1초단위로 재호출할 것인가 >> 0이라는 what을 가진 handleMessage를 1초 단위로 재호출할 것이다.
            handler.sendEmptyMessageDelayed(0, 1000);

            //Delayed가 되는 1초 사이에 아래의 코드들이 작동한다.
            set_count ++;
            //set_count가 정수이기에 String으로 변환해줘야 한다.
            count.setText(String.valueOf(set_count));
        }
    };
}