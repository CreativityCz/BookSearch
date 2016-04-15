package cuiz.testmatialdesign;

/**
 * Created by cuiz on 2016/4/15.
 */
public class BookInfo  {

    private String bookName = null;
    private int bookImage;
    private String bookIntroduce = null;

    public BookInfo(String bookName, int bookImage, String bookIntroduce) {
        this.bookName = bookName;
        this.bookImage = bookImage;
        this.bookIntroduce = bookIntroduce;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookImage() {
        return bookImage;
    }

    public void setBookImage(int bookImage) {
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
}
