package com.chocolatedevelopers.stuganizer.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.chocolatedevelopers.stuganizer.fragments.FirstSemester2;
import com.chocolatedevelopers.stuganizer.fragments.SecondSemester2;

public class PagerAdapter2 extends FragmentPagerAdapter {
    private int tabsNumber;
     public PagerAdapter2(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
         switch(position) {
             case 0:
             return new FirstSemester2();
             case 1:
                 return new SecondSemester2();
             default: return  null;
         }
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
