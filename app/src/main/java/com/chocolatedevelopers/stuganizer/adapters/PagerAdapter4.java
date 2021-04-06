package com.chocolatedevelopers.stuganizer.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.chocolatedevelopers.stuganizer.fragments.FirstSemester4;
import com.chocolatedevelopers.stuganizer.fragments.SecondSemester4;

public class PagerAdapter4 extends FragmentPagerAdapter {
    private int tabsNumber;
     public PagerAdapter4(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
         switch(position) {
             case 0:
             return new FirstSemester4();
             case 1:
                 return new SecondSemester4();
             default: return  null;
         }
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
