package cuiz.testmatialdesign.Entity;

import android.os.Parcelable;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cuiz.testmatialdesign.R;

/**
 * Created by cuiz on 2016/4/15.
 */
public class Book implements Serializable{

    //变量的命名必须与json数据的相应字段名称一致，才能通过gson得到对应字段的数据赋值。
    private String subtitle;
    private String[] author;
    private String pubdate;
    private String origin_title;
    private String image;
    private String catalog;
    private String alt;
    private String id;
    private String publisher;
    private String title;
    private String url;
    private String author_intro;
    private String summary;
    private String price;
    private String pages;
    private Images images;

    //my
    private String bookName = null;
    private String bookImage;
    private String bookIntroduce = null;

    private static final String BASE_URL = "https://api.douban.com/v2/";

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public class Images implements Serializable {
        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public interface IBookResponce<T>  {
        void onGetData(T data);
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String[] getAuthor() {
        return author;
    }

    public void setAuthor(String[] author) {
        this.author = author;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public static String getBaseUrl() {
        return BASE_URL;
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
        return "Book{" +
                "subtitle='" + subtitle + '\'' +
                ", author=" + Arrays.toString(author) +
                ", pubdate='" + pubdate + '\'' +
                ", origin_title='" + origin_title + '\'' +
                ", image='" + image + '\'' +
                ", catalog='" + catalog + '\'' +
                ", alt='" + alt + '\'' +
                ", id='" + id + '\'' +
                ", publisher='" + publisher + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", author_intro='" + author_intro + '\'' +
                ", summary='" + summary + '\'' +
                ", price='" + price + '\'' +
                ", pages='" + pages + '\'' +
                ", images=" + images +
                ", bookName='" + bookName + '\'' +
                ", bookImage='" + bookImage + '\'' +
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
    public static void searchBooks(String searchKeyWord , final IBookResponce<List<Book>> iBookResponce){
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
                        Gson gson = new Gson();
                        JSONObject jsonObject = new JSONObject(new String(responseBody));
                        /*"books" : [Book, ]*/
                        JSONArray jsonBooks = jsonObject.optJSONArray("books"); //只取书籍一栏，是许多书籍的列表。
                        List<Book> mBooks = gson.fromJson(jsonBooks.toString(),new TypeToken<List<Book>>(){}.getType());

                        /*List<Book> mbooks = new ArrayList<Book>();
                        //遍历每一个book
                        for(int i=0;i<jsonBooks.length();i++){
                            JSONObject jsonBook = jsonBooks.optJSONObject(i);

                            System.out.println("==========="+jsonBook.toString());//test

                            Book mBook = new Book();
                            mBook.setBookName(jsonBook.getString("title"));
                            mBook.setBookIntroduce(jsonBook.getString("publisher"));
                            mBook.setBookImage(jsonBook.getString("image"));
                            mbooks.add(mBook);
                        }*/
                        /*Gson gson = new Gson();
                        System.out.print("**********"+jsonBooks.toString());
                        List<BookInfo> bookInfos = gson.fromJson(jsonBooks.toString(), new TypeToken<List<BookInfo>>(){}.getType());*/
                        iBookResponce.onGetData(mBooks);
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
