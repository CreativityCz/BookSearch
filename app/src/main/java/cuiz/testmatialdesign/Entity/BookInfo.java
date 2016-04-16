package cuiz.testmatialdesign.Entity;

import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cuiz.testmatialdesign.R;

/**
 * Created by cuiz on 2016/4/15.
 */
public class BookInfo  {

    private String bookName = null;
    private String bookImage;
    private String bookIntroduce = null;

    private static final String BASE_URL = "https://api.douban.com/v2/";

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public BookInfo(String bookName, String bookImage, String bookIntroduce) {
        this.bookName = bookName;
        this.bookImage = bookImage;
        this.bookIntroduce = bookIntroduce;
    }

    public interface IBookResponce<T>  {
        void onGetData(T data);
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public String getBookIntroduce() {
        return bookIntroduce;
    }

    public void setBookIntroduce(String bookIntroduce) {
        this.bookIntroduce = bookIntroduce;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "bookName='" + bookName + '\'' +
                ", bookImage=" + bookImage +
                ", bookIntroduce='" + bookIntroduce + '\'' +
                '}';
    }


    /**-----------------------------------------------------
     *       q	    查询关键字	q和tag必传其一
             tag	查询的tag	q和tag必传其一
             start	取结果的offset	默认为0
             count	取结果的条数	默认为20，最大为100

             返回：返回status=200，
             {
             "start": 0,
             "count": 10,
             "total": 30,
             "books" : [Book, ]
             }
     * */
    //针对搜索的关键字，通过AsyncHttpClient设置好参数，搜索，传递搜索到的数据。
    public static void searchBooks(String searchKeyWord , final IBookResponce<List<BookInfo>> iBookResponce){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        requestParams.put("q",searchKeyWord);  //q	查询关键字
        requestParams.put("start",0);   //start	取结果的offset
        requestParams.put("count",20);  //count	取结果的条数
        client.get(getAbsoluteUrl("book/search"), requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, org.apache.http.Header[] headers, byte[] responseBody) {
                if(statusCode == 200){
                    try {
                        /*{
                            "start": 0,
                                "count": 10,
                                "total": 30,
                                "books" : [Book, ]
                        }*/
                        JSONObject jsonObject = new JSONObject(new String(responseBody));
                        /*"books" : [Book, ]*/
                        JSONArray jsonBooks = jsonObject.optJSONArray("books"); //只取书籍一栏，是许多书籍的列表。
                        //遍历每一个book
                        List<BookInfo> bookInfos = new ArrayList<BookInfo>();

                        for(int i=0;i<jsonBooks.length();i++){
                            JSONObject jsonBook = jsonBooks.optJSONObject(i);
                            String bookName = jsonBook.getString("title");
                            String publisher = jsonBook.getString("publisher");
                            String image = jsonBook.getString("image");

                            BookInfo bookInfo = new BookInfo(bookName,image,publisher);
                            bookInfos.add(bookInfo);
                        }
                        /*Gson gson = new Gson();
                        System.out.print("**********"+jsonBooks.toString());
                        List<BookInfo> bookInfos = gson.fromJson(jsonBooks.toString(), new TypeToken<List<BookInfo>>(){}.getType());*/
                        iBookResponce.onGetData(bookInfos);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
