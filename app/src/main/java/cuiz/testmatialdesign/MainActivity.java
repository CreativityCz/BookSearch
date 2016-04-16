package cuiz.testmatialdesign;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import cuiz.testmatialdesign.Adapter.BooksAdapter;
import cuiz.testmatialdesign.Adapter.SampleFragmentPagerAdapter;
import cuiz.testmatialdesign.Entity.BookInfo;
import me.drakeet.materialdialog.MaterialDialog;
public class MainActivity extends AppCompatActivity/*
        implements NavigationView.OnNavigationItemSelectedListener*/ {

    private RecyclerView recyclerView = null;
    FloatingActionButton fab;

    private ArrayList<BookInfo> bookInfos = null;
    BooksAdapter booksAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /**
         * recyclerView
         * */
        recyclerView = (RecyclerView) findViewById(R.id.rvBooks);
        // Attach the adapter to the recyclerview to populate items
        booksAdapter = new BooksAdapter(MainActivity.this);
        recyclerView.setAdapter(booksAdapter);
        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        fab= (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new fabClickListener());

        // Initialize contacts
        /*bookInfos = new ArrayList<>();//刚开始这一步忘写了。。傻眼，界面加载不了。
        BookInfo book1 = new BookInfo("自控力",R.drawable.ic_menu_gallery,"introduce: very good");
        BookInfo book2 = new BookInfo("花千骨（上）",R.drawable.ic_menu_slideshow,"介绍：不错");
        BookInfo book3 = new BookInfo("花千骨（下）",R.drawable.ic_menu_gallery,"介绍：相当不错");
        bookInfos.add(book1);
        bookInfos.add(book2);
        bookInfos.add(book3);*/

        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();*/

        /*NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/
    }

    class fabClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            //setTitle\setMessage 被setView方法屏蔽了
            /*materialDialog.setTitle("搜索");
            materialDialog.setMessage("hello,你好，这里是消息");*/
            /**input*/
            EditText editText;
            editText = new EditText(MainActivity.this);
            editText.addTextChangedListener(new MyTextChangedListener());
            //MaterialDialog
            final MaterialDialog materialDialog = new MaterialDialog(MainActivity.this);
            materialDialog.setView(editText);
            materialDialog.setPositiveButton("确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    materialDialog.dismiss();
                }
            });
            materialDialog.show();
        }
    }

    class MyTextChangedListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
        @Override
        public void afterTextChanged(Editable s) {
            String searchContent = s.toString();
            Snackbar.make(fab, searchContent, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            doSearch(searchContent);//搜索
        }
    }
    //搜索书籍，实现回调接口，得到响应结果。
    public void doSearch(String searchContent){
        booksAdapter.cleanItems();
        BookInfo.searchBooks(searchContent, new BookInfo.IBookResponce<List<BookInfo>>() {
            @Override
            public void onGetData(List<BookInfo> data) {
                booksAdapter.updateItems(data);
                //test
                for(BookInfo bookInfo: data){
                    System.out.println("Result::================"+bookInfo.toString());
                }
            }
        });
    }


    /*@Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/
}
