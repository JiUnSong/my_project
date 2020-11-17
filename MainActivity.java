package org.techtown.scheduledetail2;
import androidx.appcompat.app.ActionBar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    org.techtown.scheduledetail2.weatherInfo weatherInfo;
    org.techtown.scheduledetail2.trafficInfo trafficInfo;
    SurroundingInfo surroundingInfo;

    private RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);

        weatherInfo = new weatherInfo();
        trafficInfo = new trafficInfo();
        surroundingInfo = new SurroundingInfo();

        TabLayout tabs = findViewById(R.id.tabs);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, weatherInfo).commit();
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                if (position == 0) {
                    selected = weatherInfo;
                } else if (position == 1) {
                    selected = trafficInfo;
                } else if (position == 2) {
                    selected = surroundingInfo;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}