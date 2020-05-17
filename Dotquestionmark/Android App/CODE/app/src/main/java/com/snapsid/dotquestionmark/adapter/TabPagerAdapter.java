package com.snapsid.dotquestionmark.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.snapsid.dotquestionmark.fragment.LocationFragment;
import com.snapsid.dotquestionmark.fragment.NearByFragment;

public class TabPagerAdapter extends FragmentPagerAdapter {

    private static final int total_pages = 1;

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

                return new NearByFragment();


    }

    @Override
    public int getCount() {
        return total_pages;
    }

    @Override
    public CharSequence getPageTitle(int position) {

                return "NearBy";

    }
}