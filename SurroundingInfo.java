package org.techtown.scheduledetail2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;

public class SurroundingInfo extends androidx.fragment.app.Fragment {
    TabLayout tabLayout;
    private FragmentActivity myContext;
    surround_restaurant surround_restaurant;
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, android.os.Bundle savedInstanceState){
        android.view.View view = inflater.inflate(R.layout.surrounding_info, container, false);
        surround_restaurant = new surround_restaurant();
        FragmentManager fragManager = myContext.getSupportFragmentManager();
        tabLayout = view.findViewById(R.id.surrounding_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("음식점"));
        tabLayout.addTab(tabLayout.newTab().setText("카페"));
        tabLayout.addTab(tabLayout.newTab().setText("은행"));
        tabLayout.addTab(tabLayout.newTab().setText("약국"));
        tabLayout.addTab(tabLayout.newTab().setText("캠핑장"));
        tabLayout.addTab(tabLayout.newTab().setText("병원"));
        tabLayout.addTab(tabLayout.newTab().setText("펜션"));
        fragManager.beginTransaction().replace(R.id.surrounding_container, surround_restaurant).commit();

        return view;
    }
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }
}
