package com.kim.help_by_voice.fragment;

/**
 * Created by SikentKim on 2015/3/30.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.kim.help_by_voice.Activitys.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class MeFragment extends Fragment {
    private ListView mListView;
    private ArrayList<Map<String, Object>> mItems = new ArrayList<Map<String, Object>>();


    public MeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListView();
    }


    public void setListView() {
        //operate on the list View
        Map<String, Object> map;
        mItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 5; i++) {
            map = new HashMap<String, Object>();
            map.put("icon", R.drawable.ic_menu_head);
            map.put("name", "Jack");
            map.put("voice_time", "10''");
            map.put("time", "2015-3-27");
            map.put("answer_num", "1");
            mItems.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                getActivity(),
                mItems, //data
                R.layout.layout_to_mandarin,  //set layout of listview
                new String[]{"icon", "name", "voice_time", "time", "answer_num"},  //key
                new int[]{R.id.imageView_icon, R.id.textView_name, R.id.textView_voice_time, R.id.textView_time, R.id.textView_answer_num}  //填充对象的id
        );
        mListView.setAdapter(adapter);

        //set on item click listener to the list view
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}