package com.korea.ex_0713;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class IntentActivity extends AppCompatActivity {

    Button call, sms, camera, gallery, link, next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        call = findViewById(R.id.call);
        sms = findViewById(R.id.sms);
        camera = findViewById(R.id.camera);
        link = findViewById(R.id.link);
        gallery = findViewById(R.id.gallery);
        next = findViewById(R.id.next);

        call.setOnClickListener(click);
        sms.setOnClickListener(click);
        camera.setOnClickListener(click);
        link.setOnClickListener(click);
        gallery.setOnClickListener(click);
        next.setOnClickListener(click);

    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //화면 전환을 위해 반드시 필요한 클래스
            Intent i = null;

            int id = view.getId();
            
            if (id == R.id.call) {
                //다이얼 화면으로 전환
//                i = new Intent(Intent.ACTION_DIAL);
//
//                //tel: 전화번호라는 걸 판단한다.
//                i.setData(Uri.parse("tel: 010-1111-1111"));
//
//                startActivity(i);
                
                //전화를 즉시 걸어주는 작업
                i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel: 010-1111-1111"));
                startActivity(i);

                //전화를 즉시 걸어주는 것은 보안적으로 중요하기에
                //권한이 필요하다.
            } else if (id == R.id.sms) {
                i = new Intent(Intent.ACTION_SENDTO);
                                            //smsto 라 써야 해당 번호한테 문자를 보낸다고 인식
                i.setData(Uri.parse("smsto: 010-2222-2222"));
                //putExtra(): 내용 지정 가능
                //            파라미터는 key와 value로 저장을 해야하는데 key값은 sms_body로 고정이다.
                i.putExtra("sms_body", "안녕~");

                startActivity(i);
            } else if (id == R.id.camera) {
                //Camera인 척하는 내장 화면으로 이동
//                i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//                startActivity(i);

                //동영상 촬영으로 이동
                i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                startActivity(i);
            } else if (id == R.id.gallery) {
                i = new Intent(Intent.ACTION_GET_CONTENT);
                
                //모든 Type을 호출할 때 사용
                i.setType("*/*");

                startActivity(i);
            } else if (id == R.id.link) {
                i = new Intent(Intent.ACTION_VIEW);

                i.setData(Uri.parse("https://www.naver.com"));

                startActivity(i);
            } else if (id == R.id.next) {
                //다른 Activity로 전환
                                            //현재 클래스          이동할 클래스
                i = new Intent(IntentActivity.this, IntentSubActivity.class);
                startActivity(i);

            }
        }
    };
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}