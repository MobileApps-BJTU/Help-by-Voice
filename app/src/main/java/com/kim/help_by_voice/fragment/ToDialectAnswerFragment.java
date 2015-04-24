package com.kim.help_by_voice.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.kim.help_by_voice.Activitys.R;
import com.kim.help_by_voice.entity.MyApplication;
import com.kim.help_by_voice.entity.ToDialectAnswer;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
        args.putString(ARG_NAME, name);
        args.putString(ARG_PLACE, place);
        args.putString(ARG_CONTENT, content);
        args.putString(ARG_TIME, time);
        args.putInt(ARG_ANSWER_NUM, answerNum);
        args.putInt(ARG_ID, id);
        args.putInt(ARG_IMAGE_ICON, imageIcon);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(ARG_NAME);
            mPlace = getArguments().getString(ARG_PLACE);
            mContent = getArguments().getString(ARG_CONTENT);
            mTime = getArguments().getString(ARG_TIME);
            mAnswerNum = getArguments().getInt(ARG_ANSWER_NUM);
            mId = getArguments().getInt(ARG_ID);
            mImageIcon = getArguments().getInt(ARG_IMAGE_ICON);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_to_dialect_answer, container, false);
        mTextViewAnswerNum = (TextView) view.findViewById(R.id.textView_answer_num);
        mTextViewContent = (TextView) view.findViewById(R.id.textView_content);
        mTextViewName = (TextView) view.findViewById(R.id.textView_name);
        mTextViewPlace = (TextView) view.findViewById(R.id.textView_place);
        mTextViewTime = (TextView) view.findViewById(R.id.textView_time);
        mImageViewIcon = (ImageView) view.findViewById(R.id.imageView_icon);
        mListView = (ListView) view.findViewById(R.id.listView_answer);
        mButtonAnswer = (Button) view.findViewById(R.id.button_answer);

        mButtonAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAnswerButtonClicked(mContent, mId);
            }
        });
        setQuestionViews();
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
        //operate on the list View
        Map<String, Object> map;
        mItems = new ArrayList<Map<String, Object>>();
        MyApplication myApplication = (MyApplication) getActivity().getApplication();
        ArrayList<ToDialectAnswer> answerList = myApplication.getAnswersList();
        for (int i = 0; i < answerList.size(); i++) {
            ToDialectAnswer answer = answerList.get(i);
            if (answer.getQuestionId() == mId) {
                map = new HashMap<String, Object>();
                map.put("icon", answer.getIcon());
                map.put("name", answer.getName());
                if (answer.isBest()) {
                    map.put("best", getResources().getString(R.string.txt_best));
                } else {
                    map.put("best", "");
                }
                DecimalFormat myformat = new DecimalFormat("0.00");
                map.put("voice_time", myformat.format(answer.getVoice_time()) + "''");
                map.put("time", answer.getDate());
                mItems.add(map);
            }
        }

        SimpleAdapter adapter = new SimpleAdapter(
                getActivity(),
                mItems, //data
                R.layout.layout_to_dialect_answer,  //set layout of listview
                new String[]{"icon", "name", "voice_time", "time", "best"},  //key
                new int[]{R.id.imageView_icon, R.id.textView_name, R.id.textView_voice_time, R.id.textView_time, R.id.textView_best}  //填充对象的id
        );
        mListView.setAdapter(adapter);

        //set on item click listener to the list view
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                Toast.makeText(getActivity().getApplicationContext(), "默认Toast样式",
//                        Toast.LENGTH_SHORT).show();

                MyApplication myApplication = (MyApplication) getActivity().getApplication();
                ArrayList<ToDialectAnswer> answerList = myApplication.getAnswersList();
                int answerId = 0;
                for (int i = 0; i < answerList.size(); i++) {
                    if (answerList.get(i).getQuestionId() == mId) {
                        answerId = (int) (i + id);
                        break;
                    }
                }
                ToDialectAnswer answer = answerList.get(answerId);
                FileName = answer.getAudioPath();

                if (mPlayer != null) {
                    mPlayer.release();
                    mPlayer = null;
                }
                mPlayer = new MediaPlayer();
                try {
                    mPlayer.setDataSource(FileName);
                    mPlayer.prepare();
                    mPlayer.start();
                } catch (IOException e) {
                }
            }
        });
    }

    //set question views
    public void setQuestionViews() {
        mImageViewIcon.setImageResource(mImageIcon);
        mTextViewName.setText(mName);
        mTextViewTime.setText(mTime);
        mTextViewAnswerNum.setText(String.valueOf(mAnswerNum));
        mTextViewPlace.setText(mPlace);
        mTextViewContent.setText(mContent);
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
