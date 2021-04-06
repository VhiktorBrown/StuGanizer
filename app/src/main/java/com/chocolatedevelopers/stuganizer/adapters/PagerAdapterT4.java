package com.chocolatedevelopers.stuganizer.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.chocolatedevelopers.stuganizer.fragments.TimelineFirst4;
import com.chocolatedevelopers.stuganizer.fragments.TimelineSecond4;
import com.chocolatedevelopers.stuganizer.fragments.TotalGpa4;

public class PagerAdapterT4 extends FragmentPagerAdapter {
    private int tabsNumber;
    public PagerAdapterT4(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TimelineFirst4();
            case 1:
                return new TimelineSecond4();
            case 2:
                return new TotalGpa4();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
