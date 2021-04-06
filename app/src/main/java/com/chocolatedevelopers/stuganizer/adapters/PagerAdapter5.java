package com.chocolatedevelopers.stuganizer.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.chocolatedevelopers.stuganizer.fragments.FirstSemester5;
import com.chocolatedevelopers.stuganizer.fragments.SecondSemester5;

public class PagerAdapter5 extends FragmentPagerAdapter {
    private int tabsNumber;
     public PagerAdapter5(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
         switch(position) {
             case 0:
             return new FirstSemester5();
             case 1:
                 return new SecondSemester5();
             default: return  null;
         }
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
