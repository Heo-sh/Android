package com.korea.ex_0712;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    //이 메서드가 없으면 ActionBar의 오른쪽 위에 ... 표시 X
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //각 메뉴의 이벤트 처리를 위한 메서드
    //매개변수 item이 어떤 메뉴가 눌러졌는지 알고 있다.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        
        if (itemId == R.id.menu1) {
            Toast.makeText(MenuActivity.this, "앱 소개 누름", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.menu2) {
            Toast.makeText(MenuActivity.this, "이메일 누름", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.menu3) {
            //현재 띄워져 있는 activity 하나를 종료
            finish();
        }

        return true;
    }
}