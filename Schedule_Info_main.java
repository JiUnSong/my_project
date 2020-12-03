package org.techtown.scheduledetail2;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Schedule_Info_main extends androidx.fragment.app.Fragment{
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, android.os.Bundle savedInstanceState){

        android.view.View view = inflater.inflate(R.layout.main_design, container, false);
        RecyclerView recyclerview = view.findViewById(R.id.schedule_cycle);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

//
//        java.util.List<ExpandableListAdapter.Item> data = new ArrayList<>();
//        long stop_count = 14;
//        String stopCnt = String.valueOf(stop_count) + "개";
//        ExpandableListAdapter.Item places = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "50-2번", stopCnt, "23분");//routes.route[3].subroutes[1].num_stops);
//        places.invisibleChildren = new ArrayList<>();
//        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "- 도보", "- 0.1km", "1분"));
//        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "청주 시외버스터미널", "충북대학교 정문", "20분"));
//        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "- 도보", "- 0.3km", "2분"));
//
//        data.add(places);
//
//        ExpandableListAdapter.Item places2 = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "823번", stopCnt, "30분");//routes.route[3].subroutes[1].num_stops);
//        places2.invisibleChildren = new ArrayList<>();
//        places2.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "- 도보", "- 0.1km", "1분"));
//        places2.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "청주 시외버스터미널", "복대초등학교", "33분"));
//        places2.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "- 도보", "- 0.5km", "3분"));
//
//        data.add(places2);
//
//        recyclerview.setAdapter(new ExpandableListAdapter(data));
        return view;
    }
}
