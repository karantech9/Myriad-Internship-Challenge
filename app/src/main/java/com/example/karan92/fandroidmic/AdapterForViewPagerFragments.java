package com.example.karan92.fandroidmic;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

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
}
