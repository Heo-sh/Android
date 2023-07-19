package com.hsh.ex_0719;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class DrawerLayoutActivity extends AppCompatActivity {

    DrawerLayout drawer;

    Button open, close;

    LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        drawer = findViewById(R.id.drawer);
        linear = findViewById(R.id.linear);
        open = findViewById(R.id.open);
        close = findViewById(R.id.close);

        open.setOnClickListener(click);
        close.setOnClickListener(click);

        //Drag로 서랍 열지 못 하게 하기
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();

            if (id == open.getId()) {
                drawer.openDrawer(linear);
            } else if (id == close.getId()) {
                drawer.closeDrawer(linear);
            }
        }
    };
}