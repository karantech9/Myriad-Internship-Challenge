package com.example.karan92.fandroidmic;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;
import java.util.Locale;

/**
 * Created by Karan92 on 3/19/2015.
 */
public class AdapterForViewPagerFragments extends FragmentPagerAdapter {

    List<Fragment> requiredFragments;

    public AdapterForViewPagerFragments(FragmentManager fm, List<Fragment> requiredFragments) {
        super(fm);
        this.requiredFragments = requiredFragments;

    }

    @Override
    public Fragment getItem(int position) {
        return requiredFragments.get(position);
    }

    @Override
    public int getCount() {
        return requiredFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        Locale l = Locale.getDefault();
        String name;
        int size = requiredFragments.size();
        Fragment f1 = requiredFragments.get(position);
        if(position==0) {
            String s = (String) f1.getArguments().get("kingdomName");
            name = s;
        }
        else
        {
            String s = (String) f1.getArguments().get("questName");
            name=s;
        }

        return name.toUpperCase(l);
    }
}
