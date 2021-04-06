package com.chocolatedevelopers.stuganizer.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.chocolatedevelopers.stuganizer.fragments.TimelineFirst5;
import com.chocolatedevelopers.stuganizer.fragments.TimelineSecond5;
import com.chocolatedevelopers.stuganizer.fragments.TotalGpa5;

public class PagerAdapterT5 extends FragmentPagerAdapter {
    private int tabsNumber;
    public PagerAdapterT5(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TimelineFirst5();
            case 1:
                return new TimelineSecond5();
            case 2:
                return new TotalGpa5();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
