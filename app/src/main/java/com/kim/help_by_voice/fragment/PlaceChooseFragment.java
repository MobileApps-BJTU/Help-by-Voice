package com.kim.help_by_voice.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kim.help_by_voice.Activitys.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceChooseFragment extends Fragment {
    ArrayList<String> mItems = new ArrayList<String>();
    private ListView listView;
    private OnFragmentInteractionListener mListener;

    public PlaceChooseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_place_choose, container, false);
        listView = (ListView) view.findViewById(R.id.listView_place);
        setListView();

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    //get the provinces of China
    public void getChinaProvinces() {
        mItems.add(getResources().getString(R.string.pro_anhui));
        mItems.add(getResources().getString(R.string.pro_aomen));
        mItems.add(getResources().getString(R.string.pro_beijing));
        mItems.add(getResources().getString(R.string.pro_chongqing));
        mItems.add(getResources().getString(R.string.pro_fujian));
        mItems.add(getResources().getString(R.string.pro_gansu));
        mItems.add(getResources().getString(R.string.pro_guangdong));
        mItems.add(getResources().getString(R.string.pro_guangxi));
        mItems.add(getResources().getString(R.string.pro_guizhou));
        mItems.add(getResources().getString(R.string.pro_hebei));
        mItems.add(getResources().getString(R.string.pro_heilongjiang));
        mItems.add(getResources().getString(R.string.pro_henan));
        mItems.add(getResources().getString(R.string.pro_jiangsu));
        mItems.add(getResources().getString(R.string.pro_jiangxi));
        mItems.add(getResources().getString(R.string.pro_jilin));
        mItems.add(getResources().getString(R.string.pro_liaoning));
        mItems.add(getResources().getString(R.string.pro_neimenggu));
        mItems.add(getResources().getString(R.string.pro_ningxia));
        mItems.add(getResources().getString(R.string.pro_shandong));
        mItems.add(getResources().getString(R.string.pro_shanghai));
        mItems.add(getResources().getString(R.string.pro_shanxi));
        mItems.add(getResources().getString(R.string.pro_sichuan));
        mItems.add(getResources().getString(R.string.pro_Shuanxi));
        mItems.add(getResources().getString(R.string.pro_taiwan));
        mItems.add(getResources().getString(R.string.pro_tianjin));
        mItems.add(getResources().getString(R.string.pro_xianggang));
        mItems.add(getResources().getString(R.string.pro_xinjiang));
        mItems.add(getResources().getString(R.string.pro_xizang));
        mItems.add(getResources().getString(R.string.pro_yunnan));
        mItems.add(getResources().getString(R.string.pro_zhejiang));
    }

    //set the list view
    public void setListView() {
        getChinaProvinces();
        listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, mItems));
        //set on item click event
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //return the place name
                mListener.getChoosedPlace(mItems.get(position));
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void getChoosedPlace(String place);
    }

}
