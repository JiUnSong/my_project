package org.techtown.scheduledetail2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpandableListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int HEADER = 0;
    public static final int CHILD = 1;

    private java.util.List<Item> data;

    public ExpandableListAdapter(java.util.List<Item> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        android.view.View view = null;
        Context context = parent.getContext();
//        float dp = context.getResources().getDisplayMetrics().density;
//        int subItemPaddingLeft = (int) (18 * dp);
//        int subItemPaddingTopAndBottom = (int) (5 * dp);
        switch (type) {
            case HEADER:
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.traffic_list, parent, false);
                ListHeaderViewHolder header = new ListHeaderViewHolder(view);
                return header;
            case CHILD:
                // CHILD 에서 LAYOUT파일을 추가해야 함
                LayoutInflater inflaterChild = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflaterChild.inflate(R.layout.traffic_detail, parent, false);
                ListChildViewHolder child = new ListChildViewHolder(view);
                return child;
        }
        return null;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item = data.get(position);

        switch (item.type) {
            case HEADER:
                final ListHeaderViewHolder itemController = (ListHeaderViewHolder) holder;
                itemController.refferalItem = item;
                itemController.header_title.setText(item.busNum);
                itemController.num_stops.setText(String.valueOf(item.stopCnt));

                itemController.btn_expand_toggle.setOnClickListener(v -> {
                    if (item.invisibleChildren == null) {
                        item.invisibleChildren = new ArrayList<Item>();
                        int count = 0;
                        int pos = data.indexOf(itemController.refferalItem);
                        while (data.size() > pos + 1 && data.get(pos + 1).type == CHILD) {
                            item.invisibleChildren.add(data.remove(pos + 1));
                            count++;
                        }
                        notifyItemRangeRemoved(pos + 1, count);
                        itemController.btn_expand_toggle.setImageResource(R.drawable.bus);
                    } else {
                            int pos = data.indexOf(itemController.refferalItem);
                            int index = pos + 1;
                            for (Item i : item.invisibleChildren) {
                            data.add(index, i);
                            index++;
                        }
                        notifyItemRangeInserted(pos + 1, index - pos - 1);
                        item.invisibleChildren = null;
                    }
                });
                itemController.show_detail.setOnClickListener(v -> {
                    if (item.invisibleChildren == null) {
                        item.invisibleChildren = new ArrayList<Item>();
                        int count = 0;
                        int pos = data.indexOf(itemController.refferalItem);
                        while (data.size() > pos + 1 && data.get(pos + 1).type == CHILD) {
                            item.invisibleChildren.add(data.remove(pos + 1));
                            count++;
                        }
                        notifyItemRangeRemoved(pos + 1, count);
                    } else {
                        int pos = data.indexOf(itemController.refferalItem);
                        int index = pos + 1;
                        for (Item i : item.invisibleChildren) {
                            data.add(index, i);
                            index++;
                        }
                        notifyItemRangeInserted(pos + 1, index - pos - 1);
                        item.invisibleChildren = null;
                    }
                });
                break;
            case CHILD:
                final ListChildViewHolder itemController1 = (ListChildViewHolder) holder;
                itemController1.refferalItem = item;
                itemController1.departure_stop.setText(item.departure_stop);
                itemController1.arrival_stop.setText(item.arrival_stop);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).type;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    private static class ListHeaderViewHolder extends RecyclerView.ViewHolder {
        public android.widget.TextView header_title;
        public android.widget.TextView arrival_info;
        public ImageView btn_expand_toggle;
        public Item refferalItem;
        public LinearLayout show_detail;
        public android.widget.TextView num_stops;

        public ListHeaderViewHolder(android.view.View itemView) {
            super(itemView);
            header_title = (android.widget.TextView) itemView.findViewById(R.id.header_title);
            arrival_info = (android.widget.TextView) itemView.findViewById(R.id.arrival_info);
            num_stops = (android.widget.TextView) itemView.findViewById(R.id.stationNum);
            btn_expand_toggle = (ImageView) itemView.findViewById(R.id.imageView);
            show_detail = (LinearLayout) itemView.findViewById(R.id.showTrafficDetail);

        }
    }
    private static class ListChildViewHolder extends RecyclerView.ViewHolder {
        public android.widget.TextView departure_stop;
        public android.widget.TextView arrival_stop;
        public Item refferalItem;

        public ListChildViewHolder(android.view.View itemView) {
            super(itemView);
            departure_stop = (android.widget.TextView) itemView.findViewById(R.id.departure_stop);
            arrival_stop = (android.widget.TextView) itemView.findViewById(R.id.arrival_stop);
        }
    }

    public static class Item {
        public int type;
        public String busNum;
        public long stopCnt;
        public String departure_stop;
        public String arrival_stop;
        public java.util.List<Item> invisibleChildren;

        public Item(int type, String departure_stop, String arrival_stop){
            this.type = type;
            this.departure_stop = departure_stop;
            this.arrival_stop = arrival_stop;
        }
    }
}