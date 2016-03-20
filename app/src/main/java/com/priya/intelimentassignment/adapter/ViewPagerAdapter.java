package com.priya.intelimentassignment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.priya.intelimentassignment.fragments.ViewPagerFragment;

/**
 * This fragmentAdapter used to display fragments in View pager in Test1.
 * It returns same fragment with different number displayed on fragment.
 *
 * Created by Priyanka on 19 March, 2016
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch(pos) {

            case 0: return ViewPagerFragment.newInstance("1");
            case 1: return ViewPagerFragment.newInstance("2");
            case 2: return ViewPagerFragment.newInstance("3");
            case 3: return ViewPagerFragment.newInstance("4");
            default: return ViewPagerFragment.newInstance("0, Default");
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
