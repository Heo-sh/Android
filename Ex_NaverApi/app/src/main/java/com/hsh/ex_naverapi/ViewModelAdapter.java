package com.hsh.ex_naverapi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import vo.BookVO;

public class ViewModelAdapter extends ArrayAdapter<BookVO> {

    //NaverActivity.class
    Context context;

    int resource;

    BookVO vo;

    ArrayList<BookVO> list;

    //파라미터를 받는 생성자가 필요하다.
    public ViewModelAdapter(Context context, int resource, ArrayList<BookVO> list, ListView myListView) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.list = list;

        //Main에서 넘어온 ListView에게 이벤트 감지자를 등록
        myListView.setOnItemClickListener(click);
    } //생성자

    AdapterView.OnItemClickListener click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //int i는 책에 대한 자세한 내용을 보기 위해 항목을 누를 때, (맨 위에 위치한 것을 누르면 i는 0)
            String title = list.get(i).getB_title();
            String author = list.get(i).getB_author();
            String price = list.get(i).getB_price();

            //이미지 경로를 가짐
            String img = list.get(i).getB_img();

            Intent intent = new Intent(context, SubActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("author", author);
            intent.putExtra("price", price);
            intent.putExtra("img", img);

            //일반 클래스로 보내는 것이다 보니 context를 붙여야 한다.
            context.startActivity(intent);
        }
    };

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //myListView.setAdapter(adapter)를 탑재하는 순간 호출되는 메서드
        //생성자의 파라미터를 받은 list의 사이즈만큼 getView()메서드가 알아서 반복 호출

        //position: 처음 1회전할 때 포지션은 0부터 호출되어 100번이 돌면 포지션이 99가 된다.

        //xml파일을 view형태의 객체로 만들어야 한다.
        //일반 Activity에서는 아래의 코드가 가능하지만, 일반 클래스에서는 context가 필요하다.
        //LayoutInflater linf = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater linf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //convertView: book_item.xml 짝이 없는 xml파일을 view형태로 변경
        convertView = linf.inflate(resource, null);

        //convertView로 넘어온 데이터들에서 원하는 데이터들만 검색
        vo = list.get(position);
        TextView title = convertView.findViewById(R.id.book_title);
        TextView author = convertView.findViewById(R.id.book_author);
        TextView price = convertView.findViewById(R.id.book_price);
        //실제 image가 들어간 것은 아니다.
        ImageView img = convertView.findViewById(R.id.book_img);
        
        //실제로 화면에 데이터들을 띄워주기 위한 단계
        title.setText(vo.getB_title());
        author.setText(vo.getB_author());
        price.setText(vo.getB_price() + "원");
        Log.i("my", "" + list.get(6).getB_img());

        //doInBackground 호출
        new ImgAsync(img, vo).execute();
        return convertView;

    }

    //실제 image를 띄워주기 위해서는 서버 통신을 한번 더 해야 한다.
    class ImgAsync extends AsyncTask<Void, Void, Bitmap> {
        Bitmap bm;
        ImageView img;
        BookVO vo;

        public ImgAsync(ImageView img, BookVO vo) {
            this.img = img;
            this.vo = vo;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            try {
                //vo가 가지고 있는 vo.getB_img()를 통해
                //img 경로를 따라 들어가는 작업

                String raddit = "rabbit.png";

                if (vo.getB_img().equals("https://shopping-phinf.pstatic.net/main_3244498/32444986817.20230710082934.jpg")) {
                    vo.setB_img(raddit);
                }

                URL img_url = new URL(vo.getB_img());


                //Buffered가 붙으면 보조Stream이며, 속도 향상에 도움을 준다.
                //보조Stream은 기반Stream(FileInputStream, OutputStream, FileReader, FileWriter 등)을 필요로 한다.
                //img_url.openStream()의 반환형이 InputStream이다. 이 때, try catch가 필요함
                BufferedInputStream bis = new BufferedInputStream(img_url.openStream());

                //img_url을 통해서 얻어온 이미지 경로가 있다.
                //InputStream을 통해서 data를 읽어올 수 있다.
                //bis가 img의 정보를 data 단위로 저장을 해놓는다.
                //그래서 바로 ImageView 형태로 넣을 수 없다.
                //1byte 단위 Data를 img 형태로 쓸 수 있도록 하는 클래스가 Bitmap이다.

                //bis가 읽어온 data를 img로 변환하기 위해 Bitmap 형태로 변경
                bm = BitmapFactory.decodeStream(bis);

                //Stream으로 받아왔기 때문에 close를 해줘야 한다.
                bis.close();

            } catch (Exception e) {
            }


            //불러올 img가 있다면 그 img를 쓸 것이고
            if (bm != null) {
                return bm;
            }

            //불러올 img가 없을 때 기본img로 Bitmap 설정
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabbit);

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //Bitmap 객체를 ImageView로 전환
            img.setImageBitmap(bitmap);
        }
    }






}
