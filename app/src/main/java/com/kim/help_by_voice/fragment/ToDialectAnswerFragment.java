package com.kim.help_by_voice.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kim.help_by_voice.Activitys.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ToDialectAnswerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ToDialectAnswerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToDialectAnswerFragment extends Fragment {
    private static final String ARG_NAME = "name";
    private static final String ARG_PLACE = "place";
    private static final String ARG_CONTENT = "content";
    private static final String ARG_TIME = "time";
    private static final String ARG_ANSWER_NUM = "answer_num";
    private static final String ARG_ID = "id";
    private static final String ARG_IMAGE_ICON = "image_icon";
    private String mName;
    private String mPlace;
    private String mContent;
    private String mTime;
    private int mAnswerNum;
    private int mId;
    private int mImageIcon;
    private ListView mListView;
    private ArrayList<Map<String, Object>> mItems = new ArrayList<Map<String, Object>>();
    private TextView mTextViewName, mTextViewPlace, mTextViewContent, mTextViewTime, mTextViewAnswerNum;
    private ImageView mImageViewIcon;
    private OnFragmentInteractionListener mListener;
    private Button mButtonAnswer;
    private MediaPlayer mPlayer = null;
    private String FileName;

    public ToDialectAnswerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static ToDialectAnswerFragment newInstance(String name, String place, String content, String time, int answerNum, int id, int imageIcon) {
        ToDialectAnswerFragment fragment = new ToDialectAnswerFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_to_dialect_answer, container, false);
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListView();
    }

    //set list view
    public void setListView() {
    }

    //set question views
    public void setQuestionViews() {
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        public void onAnswerButtonClicked(String content, int questionId);
    }

}
