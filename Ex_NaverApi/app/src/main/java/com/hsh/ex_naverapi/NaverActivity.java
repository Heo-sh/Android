package com.hsh.ex_naverapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import parser.Parser;
import vo.BookVO;

public class NaverActivity extends AppCompatActivity {

    public static EditText search;

    ListView myListView;

    Button search_btn;

    //connectNaver 메서드를 호출하려면 parser 객체가 있어야 한다.
    Parser parser;

    //로딩 이미지를 띄우기 위한 레이아웃
    LinearLayout loading;

    //adapter 객체 준비
    ViewModelAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naver);

        search = findViewById(R.id.search);

        myListView = findViewById(R.id.myListView);

        search_btn = findViewById(R.id.search_btn);

        loading = findViewById(R.id.loading);

        parser = new Parser();

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //parser.connectNaver();
                //connectNaver()도 메서드기 때문에 메서드가 완전히 종료되어야
                //다음 작업을 할 수 있다. 데이터가 많으면 로딩이 완료되는 동안
                //다른 작업을 할 수 가 없다. 뒤로 가기도 안되고, 터치도 안되며,
                //클릭 또한 안된다. 아무 것도 할 수 가 없다.

                //execute() 메서드에 parameter를 넣어 보낼 수 있다.
                new NaverAsync().execute("횽", "길", "동");

                //로딩시작
                loading.setVisibility(View.VISIBLE);
                myListView.setVisibility(View.GONE);

                //검색 후 키보드를 안 보이게 하기
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
            }
        });
        
        //가상 키보드에서 검색 버튼을 누른 경우 실제 ok버튼이 눌리도록 강제 클릭
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEARCH) {
                    //ok 버튼 누르기
                    search_btn.performClick();
                }
                return true;
            }
        });
    }

    //AsyncTask: Background에서의 서버 통신을 위해 반드시 필요한 Class
    //AsyncTask는 세 개의 제너릭타입을 가지고 있다.
    //제너릭타입의 순서는 지켜져야 한다.
    //1. doinBackground의 파라미터 타입
    //   - AsyncTask의 첫 번째 제너릭타입과, doinBackground의 파라미터 타입은 같아야 한다.
    //2. onProgressUpdate가 Overriding 되어 있다면 이 메서드에서 사용할 타입
    //3. doinBackground의 반환형이자 작업의 최종결과를 차지하는 onPostExecute()의 파라미터 타입
    class NaverAsync extends AsyncTask<String, Void, ArrayList<BookVO>> {
        
        //(String... strings): String타입의 strings배열을 만들되, 들어온 요소의 개수를 신경쓰지 말고,
        //                     들어오는 대로 만들어라
        //결과
        //stirngs[0] = "홍";
        //stirngs[1] = "길";
        //stirngs[2] = "동";
        @Override
        protected ArrayList<BookVO> doInBackground(String... strings) {
            //필수 메서드
            //각종 반복이나 제어 등의 백그라운드에서 필요한 처리코드를 담당하는 메서드

            //parser.connectNaver()가 반환형이 ArrayList이다.
            return parser.connectNaver();
        }

        @Override
        protected void onPostExecute(ArrayList<BookVO> bookVOS) {
            //doInBackground에서 return된 최종 작업 결과를 bookVOS가 받게 된다.

            //Logcat에서 100이 뜨면 data를 가져오는데 성공한 것이다.
            Log.i("my", "" + bookVOS.size());

            //목록화를 가능하게 하기 위한 class가 하나 더 필요하다.
            //생성자를 통해 param 전달
            adapter = new ViewModelAdapter(NaverActivity.this, R.layout.book_item, bookVOS, myListView);

            //준비된 adapter를 ListView에 탑재
            myListView.setAdapter(adapter);

            //로딩 종료
            loading.setVisibility(View.GONE);
            myListView.setVisibility(View.VISIBLE);
        }
    }






















}