package com.chocolatedevelopers.stuganizer.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.chocolatedevelopers.stuganizer.fragments.TimelineFirst;
import com.chocolatedevelopers.stuganizer.fragments.TimelineSecond;
import com.chocolatedevelopers.stuganizer.fragments.TotalGpa;

public class PagerAdapterT extends FragmentPagerAdapter {
    private int tabsNumber;
    public PagerAdapterT(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TimelineFirst();
            case 1:
                return new TimelineSecond();
            case 2:
                return new TotalGpa();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
