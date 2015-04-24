package com.kim.help_by_voice.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kim.help_by_voice.Activitys.AskToDialectActivity;
import com.kim.help_by_voice.Activitys.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AskFragment extends Fragment {
    private Button mMandarinToDialectBtn;

    public AskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ask, container, false);
        mMandarinToDialectBtn = (Button)view.findViewById(R.id.btn_to_dialect);
        mMandarinToDialectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AskToDialectActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


}
