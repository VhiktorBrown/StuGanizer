package com.chocolatedevelopers.stuganizer.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.chocolatedevelopers.stuganizer.fragments.TimelineFirst2;
import com.chocolatedevelopers.stuganizer.fragments.TimelineSecond2;
import com.chocolatedevelopers.stuganizer.fragments.TotalGpa2;

public class PagerAdapterT2 extends FragmentPagerAdapter {
    private int tabsNumber;
    public PagerAdapterT2(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TimelineFirst2();
            case 1:
                return new TimelineSecond2();
            case 2:
                return new TotalGpa2();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
