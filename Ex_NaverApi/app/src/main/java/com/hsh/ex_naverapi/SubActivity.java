package com.hsh.ex_naverapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class SubActivity extends AppCompatActivity {

    ImageView img;

    TextView title, author, price;

    Button clear;

    Intent i;

    Bitmap bm;

    String i_mg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        //Main에서 넘어온 intent 정보 받기
        //ViewModelAdapter에서 넘어온 정보를 현재 Activity에서 받아온다.
        i = getIntent();
        String t = i.getStringExtra("title");
        String p = i.getStringExtra("price");
        String a = i.getStringExtra("author");
        i_mg = i.getStringExtra("img");

        img = findViewById(R.id.img);
        title = findViewById(R.id.title);
        price = findViewById(R.id.price);
        author = findViewById(R.id.author);
        clear = findViewById(R.id.clear);

        title.setText(t);
        price.setText(p + "원");
        author.setText(a);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        new ImgAsync().execute(i_mg);
    }

    class ImgAsync extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL img_url = new URL(strings[0]);
                BufferedInputStream bis = new BufferedInputStream(img_url.openStream());

                //BufferedInputStream으로 읽어온 img를 data형태로 가지고 있다.
                bm = BitmapFactory.decodeStream(bis);

//                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.rabbit);
                bis.close();
            } catch (Exception e) {
            }

            if(bm != null) {
                return bm;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            img.setImageBitmap(bitmap);
        }
    }
}