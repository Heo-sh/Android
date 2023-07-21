package com.hsh.ex_0721;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SqliteActivity extends AppCompatActivity {

    TextView res;

    Button select, search, insert, del;

    EditText name, phone, age;

    //SQLiteDatabase: Android에서 기본으로 제공하는 Class
    SQLiteDatabase mDatabase;

    //처음에 한 번 복사를 하면, 다음은 복사를 할 필요가 없기에
    //변수를 하나 만들어서 막자
    boolean isFirst = true;

    SharedPreferences pref;

    //개발자가 완벽히 알맞은 코드임에도 불구하고 충돌 가능성이 있는 코드를 사용할 때 사용하는 annotation
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        pref = PreferenceManager.getDefaultSharedPreferences(SqliteActivity.this);

        load();

        //앱이 최초 실행되었을 때 assets 폴더의 DB를 휴대폰 내부저장소에 저장
        copyAssets();

        save();

        mDatabase = openOrCreateDatabase(Environment.getExternalStorageDirectory() + "/database/myDB.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        res = findViewById(R.id.res);

        select = findViewById(R.id.select);
        search = findViewById(R.id.search);
        insert = findViewById(R.id.insert);
        del = findViewById(R.id.del);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        age = findViewById(R.id.age);

        select.setOnClickListener(myClick);
        search.setOnClickListener(myClick);
        insert.setOnClickListener(myClick);
        del.setOnClickListener(myClick);
    } //onCreate

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            
            if (id == select.getId()) {
                //전체조회
            } else if (id == search.getId()) {
                //검색
            } else if (id == insert.getId()) {
                //추가
            } else if (id == del.getId()) {
                //삭제
            }
        }
    };

    //Query문 수행을 위한 메서드
    public void SearchQuery(String query) {
        Cursor c = mDatabase.rawQuery(query, null);
        //지금 DB만 복사해놓은 상태이고, 실제 어디로 연결해야 하는지 지정 X
        //mDatabase를 통해서 rawQuery라는 메서드를 가지고 Query문을 요청해야 함
    }

    //assets 폴더의 DB를 휴대폰 내부저장소에 저장하기 위한 메서드
    public void  copyAssets() {
        //InputStream으로 읽어서 OutputStream으로 저장하기

        //getAssets(): assets 폴더 밑의 파일 가져오기
        AssetManager assetManager = getAssets();
        String[] files = null;
        String path = "";

        try {
            //files[0] -> "images" 폴더가 숨겨져있다고 한다.
            //files[1] -> "myDB.db" 파일 이름이 문자열로 들어온다.
            files = assetManager.list("");

            InputStream in = null;
            OutputStream out = null;

            //files[1]의 값인 "myDB.db"와 같은 이름의 파일을 찾아서
            //inputStream으로 읽어오기
            in = assetManager.open(files[1]);

            //내부저장소에 폴더 생성
            //휴대폰 내부 저장소의 최상위(root)폴더에 접근
            String str = "" + Environment.getExternalStorageDirectory();
            //database라는 이름의 폴더 같이 생성하기
            path = str + "/database";

            File mpath = new File(path);

            if (!mpath.exists()) {
                isFirst = true;
            }
            if (isFirst) {
                //실질적으로 database 폴더 생성
                mpath.mkdirs();

                //database 이름의 폴더까지 접근해서 myDB.db라는 이름으로 output할 준비
                out = new FileOutputStream(path + "/" + files[1]);

                //2mb
                byte[] buffer = new byte[2048];
                int read =  0;

                while ((read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }

                out.close();
                in.close();
                isFirst = false;
            }
        } catch (Exception e) {

        }


    } //copyAssets()

    public void save() {
        SharedPreferences.Editor edit = pref.edit();
        //save라는 이름으로 isFirst 저장
        edit.putBoolean("save", isFirst);
        edit.commit();
        //copyAssets()를 실행시키고 저장을 하기 때문에 isFirst에 false가 저장된다.
    }

    //isFirst 값 load
    public void load() {
        isFirst = pref.getBoolean("save", true);
    }





























}