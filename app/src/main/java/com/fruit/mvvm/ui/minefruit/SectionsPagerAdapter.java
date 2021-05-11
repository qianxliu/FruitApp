package com.fruit.mvvm.ui.minefruit;

import android.content.Context;
import android.os.Parcelable;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fruit.mvvm.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_trading, R.string.tab_text_growing, R.string.tab_text_success};
    private final Context mContext;
    // ArrayList继承了List接口
    ArrayList<Fragment> data = new ArrayList<>();


    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
        data.add(PlaceholderFragment.newInstance(0));
        data.add(PlaceholderFragment.newInstance(1));
        data.add(PlaceholderFragment.newInstance(2));
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // getItem 用以初始化给定页面的fragment
        // Return a PlaceholderFragment (defined as a static inner class below).
        // 为当前position (0即为1) 返回一个PlaceholderFragment
        // 做判断得到多个PlaceholderFragment
        return data.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return TAB_TITLES.length;
    }

    @Override
    public void restoreState(@Nullable @org.jetbrains.annotations.Nullable Parcelable state, @Nullable @org.jetbrains.annotations.Nullable ClassLoader loader) {
        super.restoreState(state, loader);
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
        super.destroyItem(container, position, object);
    }
}