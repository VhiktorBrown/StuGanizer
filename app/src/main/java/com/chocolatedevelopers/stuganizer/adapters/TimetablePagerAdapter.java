package com.chocolatedevelopers.stuganizer.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.chocolatedevelopers.stuganizer.fragments.Friday;
import com.chocolatedevelopers.stuganizer.fragments.Monday;
import com.chocolatedevelopers.stuganizer.fragments.Saturday;
import com.chocolatedevelopers.stuganizer.fragments.Thursday;
import com.chocolatedevelopers.stuganizer.fragments.Tuesday;
import com.chocolatedevelopers.stuganizer.fragments.Wednesday;

public class TimetablePagerAdapter extends FragmentPagerAdapter {
    private int tabsNumber;
    public TimetablePagerAdapter(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return  new Monday();
            case 1:
                return new Tuesday();
            case 2:
                return new Wednesday();
            case 3:
                return new Thursday();
            case 4:
                return new Friday();
            case 5:
                return new Saturday();
            default: return  null;

        }
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
