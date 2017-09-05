package com.example.tablayoutviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



/**
 * Created by Indogroup02 on 04/09/2017.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;

    public TabPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return FirstFragment.newInstance(0, "Page # 1");
            case 1: // Fragment # 1 - This will show SecondFragment
                return SecondFragment.newInstance(1, "Page # 2");
            case 2: // Fragment # 2
                return ThirdFragment.newInstance(2, "Page # 3");
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "Item uno";
        } else if(position == 1){
            return "Item Dos";
        } else if (position == 2){
            return "";
        }
        return "Item Otro";
    }

}
