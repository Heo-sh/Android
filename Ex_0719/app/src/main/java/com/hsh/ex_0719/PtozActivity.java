package com.hsh.ex_0719;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

public class PtozActivity extends AppCompatActivity {

    ImageView img;

    //photoview.jar 안에 존재하는 class
    PhotoViewAttacher attacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptoz);

        img = findViewById(R.id.img);

        //attacher 객체를 생성하고 생성자에 ImageView를 준다.
        attacher = new PhotoViewAttacher(img);

        attacher.update();
    }
}