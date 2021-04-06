package com.chocolatedevelopers.stuganizer.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.chocolatedevelopers.stuganizer.fragments.FirstSemester;
import com.chocolatedevelopers.stuganizer.fragments.SecondSemester;

public class PagerAdapter extends FragmentPagerAdapter {
    private int tabsNumber;
     public PagerAdapter(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
         switch(position) {
             case 0:
             return new FirstSemester();
             case 1:
                 return new SecondSemester();
             default: return  null;
         }
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
