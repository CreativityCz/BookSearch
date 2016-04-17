package cuiz.testmatialdesign;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import cuiz.testmatialdesign.Adapter.SampleFragmentPagerAdapter;
import cuiz.testmatialdesign.Entity.Book;

/**
 * Created by cuiz on 2016/4/15.
 */
public class BookDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //左上角返回箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //点击箭头返回执行的动作
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        Book book = (Book) getIntent().getSerializableExtra("BOOK");
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle(book.getTitle());

        ImageView imageView = (ImageView) findViewById(R.id.iv_book_detail_image);
        Glide.with(imageView.getContext())
                .load(book.getImages().getLarge())
                .fitCenter()
                .into(imageView);


        /**--------------------------*/

        /**
         * TabLayout--Viewpager
         * */

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),this,book));

        tabLayout.setupWithViewPager(viewPager);

    }
}
