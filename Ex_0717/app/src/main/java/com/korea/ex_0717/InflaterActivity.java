package com.korea.ex_0717;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class InflaterActivity extends AppCompatActivity {
    //메뉴 만들 때 inflater를 사용했었다.
    //짝이 없는 xml을 view형태의 객체로 만들어서 레이아웃에 붙혔다.
    //앞으로 서버랑 통신하면서 눈에 보이지 않는 xml을 view형태로 만들어서
    //화면에 보여줘야 하는 경우가 있다.

    LinearLayout layout;

    Button btn;

    //xml 문서를 view 구조로 casting해준느 객체
    LayoutInflater inflater;

    View inner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflater);


        //안드로이드에서 xml을 view로 만들어주기 위해서 갖고 잇는 상수 값을 통해서
        //앞으로 나한테 주어진 xml문서는 inflater 기능을 통해서 눈으로 확인 가능한 레이아웃이 될거다.
        inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

        //아무 것도 가지지 않은 비어 있는 Layout
        //여기에 Activity와 쌍을 이루고 있지 않은 Layout만 별도로 하나 만들 것
        //Layout은 별도로 만들어봤자 누군가 참조해주지 않으면 눈에 보이지 않는다.
        //그래서 짝이 없는 Layout을 만든다는 거 자체가 생소한 작업일 수 있다.
        layout = findViewById(R.id.layout);

        //inner = inflater.inflate(R.layout.my_inflator, null);
        //null 값에 layout을 넣어도 된다.
        inner = inflater.inflate(R.layout.my_inflator, layout);

        //LinearLayout 안 쪽에 inner라고 생성해둔 my_inflater 추가
        //layout.addView(inner);

        //경로를 잘 명시해줘야 한다.
        btn = inner.findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(InflaterActivity.this, "Test", Toast.LENGTH_SHORT).show();
            }
        });

        //버튼이 inner라고 하는 객체 안에 있기 때문에 inner 밑에다가 검색을 해줘야 한다!
        //누구한테 가져온건지 확실하게 명시를 해줘야 한다.
        //버쪽 통신을 보면서 사용할 기술중에서 정말 핵심적으로 사용될 기술입니다.
    }

























}