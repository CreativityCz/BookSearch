package cuiz.testmatialdesign;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by cuiz on 2016/4/15.
 */
public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    List<BookInfo> bookInfos = null;
    Context context = null;
    int itemLayout;


    public BooksAdapter(Context context,List<BookInfo> bookInfoList,int layoutId) {
        this.context = context;
        this.bookInfos = bookInfoList;
        this.itemLayout = layoutId;
    }


    @Override
    public BooksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the item layout（加载一个布局文件，生成View）
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);

        //and create the holder （创建View的持有者）
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BooksAdapter.ViewHolder holder, int position) {
        //set the view attributes based on the data
        holder.imageView.setImageResource(bookInfos.get(position).getBookImage());
        holder.textView1.setText(bookInfos.get(position).getBookName());
        holder.textView2.setText(bookInfos.get(position).getBookIntroduce());
    }

    @Override
    public int getItemCount() {
        return bookInfos.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        ImageView imageView;
        TextView textView1;
        TextView textView2;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.book_image);
            textView1 = (TextView) itemView.findViewById(R.id.book_name);
            textView2 = (TextView) itemView.findViewById(R.id.book_introduce);
        }
    }

}
