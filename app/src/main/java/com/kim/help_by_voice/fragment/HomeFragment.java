package com.kim.help_by_voice.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.kim.help_by_voice.Activitys.R;
import com.kim.help_by_voice.Activitys.ToDialectActivity;
import com.kim.help_by_voice.entity.MyApplication;
import com.kim.help_by_voice.entity.ToDialectQuestion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HomeFragment extends Fragment {
    private ListView mListView;
    private ArrayList<Map<String, Object>> mItems = new ArrayList<Map<String, Object>>();


    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mListView = (ListView) view.findViewById(R.id.listView_home);
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
        MyApplication myApplication =(MyApplication) getActivity().getApplication();
        ArrayList<ToDialectQuestion> questionList = myApplication.getQuestionList();
        mItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i <questionList.size(); i++) {
            ToDialectQuestion question = questionList.get(i);
            map = new HashMap<String, Object>();
            map.put("icon", question.getIcon());
            map.put("name", question.getName());
            map.put("place", question.getPlace());
            map.put("content", question.getContent());
            map.put("time", question.getDate());
            map.put("answer_num", String.valueOf(question.getAnswer_num()));
            mItems.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                getActivity(),
                mItems, //data
                R.layout.layout_to_dialect,  //set layout of listview
                new String[]{"icon", "name", "place", "content", "time", "answer_num"},  //key
                new int[]{R.id.imageView_icon, R.id.textView_name, R.id.textView_place, R.id.textView_content, R.id.textView_time, R.id.textView_answer_num}  //填充对象的id
        );
        mListView.setAdapter(adapter);

        //set on item click listener to the list view
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyApplication myApplication =(MyApplication) getActivity().getApplication();
                ArrayList<ToDialectQuestion> questionList = myApplication.getQuestionList();
                ToDialectQuestion question = questionList.get(position);
                Intent intent = new Intent(getActivity(), ToDialectActivity.class);
                intent.putExtra("id",question.getId());
                startActivity(intent);
            }
        });
    }


}
