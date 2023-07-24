package com.hsh.ex_0724;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class ViewPagerActivity extends AppCompatActivity {

    LinearLayout visible_layout;

    Animation menu_visible_ani;

    Button btn_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        visible_layout = findViewById(R.id.visible_layout);
        btn_menu = findViewById(R.id.btn_menu);

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (visible_layout.getVisibility() == View.VISIBLE) {
                    //Menu가 보여지고 있을 때는 숨긴다.
                    //menu_visible_ani = AnimationUtils.loadAnimation(ViewPagerActivity.this, R.anim.menu_visible);
                    //visible_layout.startAnimation(menu_visible_ani);
                    visible_layout.setVisibility(View.INVISIBLE);
                } else {
                    //Menu가 숨겨진 상태면 보여준다.
                    menu_visible_ani = AnimationUtils.loadAnimation(ViewPagerActivity.this, R.anim.menu_invisible);
                    visible_layout.startAnimation(menu_visible_ani);
                    visible_layout.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}