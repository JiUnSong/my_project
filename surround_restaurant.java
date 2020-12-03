package org.techtown.scheduledetail2;

import android.os.AsyncTask;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;


public class surround_restaurant extends Fragment {
    public TextView hot_food;
    public TextView hot_star;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup surrounding_container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.surround_restaurant, surrounding_container, false);
        regionData task = new regionData();
        hot_food = view.findViewById(R.id.surrounding_title);
        hot_star = view.findViewById(R.id.surrounding_star);
        task.execute();


        return view;
    }

    public class regionData extends AsyncTask<TextView, TextView, ArrayList<Surround_Data>>{

        @Override
        protected ArrayList<Surround_Data> doInBackground(TextView... values) {
            ArrayList<Surround_Data> arrayList = new ArrayList<Surround_Data>();

            try {
                Document document = Jsoup.connect("https://www.siksinhot.com/taste?hpAreaId=662&upHpAreaId=17#").get();
                Elements doc = document.select("div.sub_cont_gray01 div.listTy1 ul li .cont a");

                String store_name = null;
                String star = null;
                String img_url = null;

                for(int i = 0; i < doc.size(); i++){
                    store_name = doc.get(i).select(".cnt .box_tit .store").text();
                    hot_food.setText(store_name);
                    star = doc.get(i).select(".cnt .score").text();
                    hot_star.setText(star);
                    img_url = doc.get(i).select(".img img").get(0).attr("src");
                    arrayList.add(new Surround_Data(store_name, star, img_url));
                }

            }catch (Exception e){
                e.printStackTrace();
            }
//            hot_food.setText(arrayList.get(5).getStore_name());
//            hot_star.setText(arrayList.get(5).getStar());
            return arrayList;
        }

    }
}
