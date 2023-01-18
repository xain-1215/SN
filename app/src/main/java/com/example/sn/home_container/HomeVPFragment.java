package com.example.sn.home_container;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.sn.R;


public class HomeVPFragment extends Fragment {
    RelativeLayout constraintLayout;
    HomeViewPagerAdapter homeViewPagerAdapter;
    ViewPager2 viewPager2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_container, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        constraintLayout = view.findViewById(R.id.home_container_layout);

        viewPager2 = view.findViewById(R.id.home_container_vp2);
        homeViewPagerAdapter = new HomeViewPagerAdapter(this);
        viewPager2.setOffscreenPageLimit(2);//預載選單頁面
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());
//        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        viewPager2.setAdapter(homeViewPagerAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewPager2.setCurrentItem(0);
    }

    public ViewPager2 getViewPager2() {
        return viewPager2;
    }
}
