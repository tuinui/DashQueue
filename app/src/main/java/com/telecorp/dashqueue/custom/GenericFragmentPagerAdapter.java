package com.telecorp.dashqueue.custom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.telecorp.dashqueue.utils.SafeCollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class GenericFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();

    public GenericFragmentPagerAdapter(FragmentManager fm, List<? extends Fragment> fragments) {
        super(fm);
        this.fragments.clear();
        this.fragments.addAll(fragments);
    }

    @Override
    public Fragment getItem(int position) {
        if (SafeCollectionUtils.isAvailableData(fragments, position)) {
            return fragments.get(position);
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return SafeCollectionUtils.getSize(fragments);
    }
}