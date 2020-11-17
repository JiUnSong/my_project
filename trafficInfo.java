package org.techtown.scheduledetail2;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class trafficInfo extends androidx.fragment.app.Fragment {

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, android.os.Bundle savedInstanceState){

        android.view.View view = inflater.inflate(R.layout.traffic_info, container, false);
        RecyclerView recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        java.util.List<ExpandableListAdapter.Item> data = new ArrayList<>();
        ExpandableListAdapter.Item places = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "버스번호", "14");//routes.route[3].subroutes[1].num_stops);
        places.invisibleChildren = new ArrayList<>();
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "- 삼성역", "- 역삼역"));
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "- 삼성역", "- 역삼역"));
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "- 삼성역", "- 역삼역"));
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "- 삼성역", "- 역삼역"));

        data.add(places);

//        ExpandableListAdapter.Item info = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "147번");
//        info.invisibleChildren = new ArrayList<>();
//        info.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "- 금호역"));
//        info.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "- 약수역"));
//        info.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "- 청구역"));
//        info.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "- 몰라"));
//
//        data.add(info);

        recyclerview.setAdapter(new ExpandableListAdapter(data));
        return view;
    }

}
