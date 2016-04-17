package cuiz.testmatialdesign;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cuiz.testmatialdesign.Entity.Book;

// In this case, the fragment displays simple text based on the page
public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String ARG_CONTENT = "CONTENT";
    private int mPage;
    private String mContent;

    public static PageFragment newInstance(int page,String content) {
        Bundle args = new Bundle();
        args.putString(ARG_CONTENT,content);
        args.putInt(ARG_PAGE, page);

        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage  = getArguments().getInt(ARG_PAGE);
        mContent = getArguments().getString(ARG_CONTENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        TextView textView = (TextView) view;
        textView.setText("Fragment #" + mPage);
        textView.append(mContent);
        return view;
    }
}