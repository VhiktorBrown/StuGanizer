package com.chocolatedevelopers.stuganizer.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.chocolatedevelopers.stuganizer.fragments.TimelineFirst3;
import com.chocolatedevelopers.stuganizer.fragments.TimelineSecond3;
import com.chocolatedevelopers.stuganizer.fragments.TotalGpa3;

public class PagerAdapterT3 extends FragmentPagerAdapter {
    private int tabsNumber;
    public PagerAdapterT3(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TimelineFirst3();
            case 1:
                return new TimelineSecond3();
            case 2:
                return new TotalGpa3();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
