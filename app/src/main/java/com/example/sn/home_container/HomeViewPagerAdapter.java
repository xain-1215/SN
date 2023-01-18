package com.example.sn.home_container;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.sn.home.HomeFragment;
import com.example.sn.home_container.HomeVPFragment;
import com.example.sn.scan.ScanFragment;

public class HomeViewPagerAdapter extends FragmentStateAdapter {

    HomeFragment homeFragment;
    ScanFragment scanFragment;
    Fragment fragment;

    public HomeViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
        this.fragment = fragment;
        scanFragment = new ScanFragment((HomeVPFragment) fragment);
        homeFragment = new HomeFragment((HomeVPFragment) fragment);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return homeFragment;
            case 1:
                return scanFragment;
            default:
                throw new IllegalArgumentException("Strange position:" + position);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}