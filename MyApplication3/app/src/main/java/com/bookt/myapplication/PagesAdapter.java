package com.bookt.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagesAdapter extends FragmentPagerAdapter {


    int numberOfTabs;

    public PagesAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.numberOfTabs = NumOfTabs;
    }

    public PagesAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ReserveFragment tab1 = new ReserveFragment();
                return  tab1;
            case 1:
                MenuFragment tab2 = new MenuFragment();
                return tab2;
            case 2:
                GalleryImagesFragment tab3 = new GalleryImagesFragment();
                return tab3;
            default:
                return null;
        }
    }



    @Override
    public int getCount() {
        return numberOfTabs;
    }

}
