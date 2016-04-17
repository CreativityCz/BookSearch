package cuiz.testmatialdesign.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cuiz.testmatialdesign.Entity.Book;
import cuiz.testmatialdesign.PageFragment;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "内容简介", "作者简介", "目录" };
    private Context context;
    private Book mBook;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context, Book book) {
        super(fm);
        this.context = context;
        mBook = book;
    }

    /**
     * The most important methods to implement here are：
     *     getPageTitle(int position) which is used to get the title for each tab.(supply to the tablayout(ad by cz))
     *     getItem(int position) which determines the fragment for each tab.
     * */

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1,getContentStr(position));
    }

    private String getContentStr(int position){
        String content = null;
        switch (position){
            case 0:
                content = mBook.getSummary();
                break;
            case 1:
                content = mBook.getAuthor_intro();
                break;
            case 2:
                content = mBook.getSummary(); //============test
                break;
            default:
                content = "空";
                break;
        }
        return content;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}