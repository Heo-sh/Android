package parser;

import com.hsh.ex_naverapi.NaverActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import vo.BookVO;

public class Parser {
    //웹에서 요소(제목, 저자, 이미지, 가격)를 검색하여 vo에 저장 및 List에 저장

    BookVO vo;
    //검색어
    String myQuery = "";


    //서버에 연결해서 xml파일도 불러오고, 자바로 파싱해서 필요한 요소만 vo에 담고
    //ArrayList에 담아서 반환해주는 작업
    public ArrayList<BookVO> connectNaver() {

        ArrayList<BookVO> list = new ArrayList<>();

        try {
            myQuery = URLEncoder.encode(NaverActivity.search.getText().toString(), "UTF-8");

            String urlstr = "https://openapi.naver.com/v1/search/book.xml?query=" + myQuery + "&display=100";

            //진짜 연결하려면 URL이라는 클래스가 있어야 한다.
            //URL클래스로 urlstr을 전달하면 CONNECTION이라고 하는 객체가 URL을 가지고 연결한다.
            URL url = new URL(urlstr);

            //url에 대한 정보가 url.openConnection()이 알고 잇으니 통로를 열어주겟다.
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            //발급받은 ID와 SECRET을 서버로 전달
            connection.setRequestProperty("X-Naver-Client-Id", "JdGkNfjscEyfMt0FxTt3");
            connection.setRequestProperty("X-Naver-Client-Secret", "acajn_LYuA");

            //ID와 비밀번호가 맞다면 connection을 통해서 연결이 될 것이고,
            //연결이 됐다면, 받은 자원들은 xml형식으로 넘어오기 때문에 java 코드로
            //바꿔주는 작업이 필요하다. 이 작업을 parsing이라고 한다.

            //위의 URL을 수행하여 받은 자원들을 JAVA 코드로 Parsing
            //싱글톤으로 생성
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            //factory는 정보를 통째로 가져오는 객체이고,
            //pullparser는 item별로 하나씩 데이터를 가지고 올 수 있도록 하는 객체
            XmlPullParser parser = factory.newPullParser();

            //connection 객체가 접속 후 가지게 된 내용을 parser가 Stream으로 읽어온다.
            //null은 인코딩 타입이라 정의하지 않아도 된다.
            parser.setInput(connection.getInputStream(), null);

            //Parser 객체를 통해서 각 요소 별 접근을 하게 되었고, 태그(요소) 내부의 값들을 가져온다.
            //while문을 돌리면서 더 이상 읽어올 책이 없을 때까지 모든 정보를 다 가져올 것이다.
            int parserEvent = parser.getEventType();
                                                //문서의 끝까지 읽어 올 때
            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                //시작태그의 이름을 가져와 vo에 담을 수 있는 정보라면 vo에 추가하겠다.
                if (parserEvent == XmlPullParser.START_TAG) {
                    String tagName = parser.getName();

                    if (tagName.equals("title")) {
                        vo = new BookVO();
                        //nextText(): 태그 안의 내용을 읽어오는 메서드
                        String title = parser.nextText();
                        vo.setB_title(title);
                    } else if (tagName.equals("image")) {
                        String image = parser.nextText();
                        vo.setB_img(image);
                    } else if (tagName.equals("author")) {
                        String author = parser.nextText();
                        vo.setB_author(author);
                    } else if (tagName.equals("discount")) {
                        String price = parser.nextText();
                        vo.setB_price(price);
                        list.add(vo);
                    }

                } //if(.START_TAG)
                
                //다음 요소를 가져올 때 순서대로 가져와라는 의미
                parserEvent = parser.next();

            } //while

        } catch (Exception e) {

        }

        return list;
    }
}
