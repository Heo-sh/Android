package com.korea.ex_0712;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class PopupMenuActivity extends AppCompatActivity {

    Button btn_menu, anchor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_menu);

        btn_menu = findViewById(R.id.btn_menu);
        anchor = findViewById(R.id.anchor);

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                                //Context context, View anchor: menu를 띄워줄 기준 객체
                PopupMenu popup = new PopupMenu(PopupMenuActivity.this, anchor); //view: btn_menu, anchor

                //팝업메뉴에 띄워줄 메뉴xml파일 등록
                //inflate(): xml을 view형태로 바꿔주는 메서드
                //popup.getMenu(): menu가 들어갈 공간을 만들어 주는 메서드
                getMenuInflater().inflate(R.menu.my_menu, popup.getMenu());

                //이벤트 처리할 시 show() 이전에 기능을 만들어야 한다.
                //이 후에 만들게 되면 에러가 나는 경우가 많다.
                popup.setOnMenuItemClickListener(menu_click);

                popup.show();
            }
        });
    }
        PopupMenu.OnMenuItemClickListener menu_click = new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                
                if (itemId == R.id.menu1) {
                    Toast.makeText(PopupMenuActivity.this, "앱 소개 누름", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.menu2) {
                    Toast.makeText(PopupMenuActivity.this, "이메일 누름", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.menu3) {
                    //현재 띄워져 있는 액티비티 한 개 종료
                    finish();
                }
                return false;
            }
        };
}