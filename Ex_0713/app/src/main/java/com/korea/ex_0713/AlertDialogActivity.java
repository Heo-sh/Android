package com.korea.ex_0713;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AlertDialogActivity extends AppCompatActivity {

    Button btn_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);

        btn_show = findViewById(R.id.btn_show);

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Button click 시 Dialog 띄우기
                                                                    //Context: 어디에 띄울 것인지
                //AlertDialog 객체 안에 Builder 클래스가 static으로 정의되어 있어서
                //객체 생성 없이 사용 가능
                AlertDialog.Builder dialog = new AlertDialog.Builder(AlertDialogActivity.this);

                //AlertDialog의 객체를 Builder 클래스가 대신 만들어준다.

                //Builder 패턴
                //별도의 Builder 객체에서 복잡한 객체의 일부를 만들고 조합하는 것을 캡슐화라 함.
                //클래스는 객체를 직접 생성하는 대신에 Builder 객체에 객체 생성을 위임

                //Builder 패턴의 장점
                //1. 제품의 내부 표현을 변경할 수 있다.
                //2. 구성 및 표현을 위한 코드를 캡슐화 한다.

                //AlertDialog의 내부의 Builder 클래스에서는 여러가지 메서드 제공

                dialog.setTitle("다이얼로그 제목");
                dialog.setMessage("다이얼로그 본문(Contents)");

                //Dialog Button 추가
                //- postive나 negative라는 명칭만 가지고 실제 긍정, 부정의 의미를 가진 게 아니고
                //  위치만 고정되어 있으니,
                //  네, 아니오의 위치를 바꾸려면 text만 바꿔주면 된다.
                //- 하나의 버튼을 만들면 그 버튼을 다시 만들 수 없다
                //- 다시 만들게 되면 최근에 작성한 걸로 갱신된다.
                //setPositiveButton(): 위치 고정
                dialog.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override                                           //int i: Click한 Button에 대한 정보가 담긴 정수형 변수
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(AlertDialogActivity.this, "평가 하기", Toast.LENGTH_SHORT).show();
                    }
                });

                //아니오 버튼을 누르면 다시 앱으로 돌아옴
                //실행할 이벤트가 없기에 이벤트 처리(new ClickListener)를 할 필요가 없다.
                //감지자를 만들지 말고 null을 넣어주면 된다.
                dialog.setNegativeButton("아니오", null);

                dialog.setNeutralButton("나중에", null);

                //dialog가 뒤로 가기 버튼, 빈 공간 터치로 사라지는 것을 방지
                dialog.setCancelable(false);

                //show(): Menu, Toast와 같이 위에 뭐가 있던지 기존의 Layout을 뚫고 보여주는 View들을
                //        팝업 or 위젯이라 해서 show()를 사용하지 않으면 보이지 않는다.
                dialog.show();
            }
        });
    }

    //뒤로 가기 버튼을 눌렀을 때 호출되는 메서드
    @Override
    public void onBackPressed() {
        //super.onBackPressed(); -> 이 메서드가 없으면 종료가 X
        //Toast.makeText(AlertDialogActivity.this, "뒤로 가기 누름", Toast.LENGTH_SHORT).show();

        //뒤로 가기 버튼을 눌렀을 때 종료하시겠습니까? 물어보는 Dialog 생성
        //네 버튼 누르면 종료
        AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogActivity.this);

        builder.setTitle("종료하시겠습니까?");

        builder.setPositiveButton("아니오", null);

        builder.setNegativeButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AlertDialogActivity.super.onBackPressed(); //or finish(): 현재 켜져 있는 모든 액티비티 종료;
            }
        });

        builder.show();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}