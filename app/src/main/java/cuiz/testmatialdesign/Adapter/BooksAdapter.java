package cuiz.testmatialdesign.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cuiz.testmatialdesign.Entity.Book;
import cuiz.testmatialdesign.MainActivity;
import cuiz.testmatialdesign.R;

/**
 * Created by cuiz on 2016/4/15.
 */
public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private List<Book> mBooks = new ArrayList<>(); //---分配内存，我想是因为若传过来的列表是空的话，可能会引起异常--by cz
    private MainActivity currentContext = null;

    public BooksAdapter(){}

    public BooksAdapter(Context context) {
        this.currentContext = (MainActivity)context;
    }

    public void cleanItems(){
        mBooks.clear();
        notifyDataSetChanged();
    }

    public void updateItems(List<Book> mBooks){
        this.mBooks.addAll(mBooks);
        notifyDataSetChanged();
    }


    @Override
    public BooksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the item layout（加载一个布局文件，生成View）
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book,parent,false);

        //and create the holder （创建View的持有者）
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(BooksAdapter.ViewHolder holder, final int position) {
        //set the view attributes based on the data
        final Book book = mBooks.get(position);
        holder.tvBookTitle.setText(book.getTitle());
        holder.tvBookIntro.setText("出版商:"+book.getPublisher()
                +"\n出版日期:"+book.getPubdate()
                +"\n作者:"+book.getAuthor()[0]
                +"\n价格:"+book.getPrice());

        Glide.with(holder.ivBookImage.getContext())
                .load(book.getImages().getMedium())
                .fitCenter()
                .into(holder.ivBookImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentContext.showBookDetail(book);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        ImageView ivBookImage;
        TextView tvBookTitle;
        TextView tvBookIntro;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            ivBookImage = (ImageView) itemView.findViewById(R.id.book_image);
            tvBookTitle = (TextView) itemView.findViewById(R.id.book_title);
            tvBookIntro = (TextView) itemView.findViewById(R.id.book_introduce);


        }
    }

}
