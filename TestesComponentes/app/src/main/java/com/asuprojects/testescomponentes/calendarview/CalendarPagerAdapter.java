package com.asuprojects.testescomponentes.calendarview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CalendarPagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> pages = new ArrayList<>();
    List<String> titles = new ArrayList<>();


    public CalendarPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    public void addPage(Fragment page, String title) {
        pages.add(page);
        titles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position);
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
