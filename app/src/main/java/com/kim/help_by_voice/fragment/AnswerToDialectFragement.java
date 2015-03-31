package com.kim.help_by_voice.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kim.help_by_voice.Activitys.R;
import com.kim.help_by_voice.entity.MyApplication;
import com.kim.help_by_voice.entity.ToDialectAnswer;
import com.kim.help_by_voice.entity.ToDialectQuestion;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AnswerToDialectFragement.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AnswerToDialectFragement#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnswerToDialectFragement extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CONTENT = "content";
    private static final String ARG_QUESTION_ID = "question_id";

    private String mContent;
    private int mQuestionId;
    private boolean mIsRecording = false;
    private Button mButtonRecord, mButtonSubmint, mButtonCancel;
    private MediaRecorder mRecorder;
    private TextView mTextViewContent;
    private String FileName = null;

    private OnFragmentInteractionListener mListener;

    public static AnswerToDialectFragement newInstance(String content, int id) {
        AnswerToDialectFragement fragment = new AnswerToDialectFragement();
        Bundle args = new Bundle();
        args.putString(ARG_CONTENT, content);
        args.putInt(ARG_QUESTION_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    public AnswerToDialectFragement() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContent = getArguments().getString(ARG_CONTENT);
            mQuestionId = getArguments().getInt(ARG_QUESTION_ID);
        }
        MyApplication myApplication = (MyApplication) getActivity().getApplication();
        ArrayList<ToDialectAnswer> answerList = myApplication.getAnswersList();
        FileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        FileName = FileName + "/audiorecord" + String.valueOf(answerList.size()) + ".3gp";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_answer_to_dialect_fragement, container, false);
        mButtonRecord = (Button) view.findViewById(R.id.button_record);
        mButtonCancel = (Button) view.findViewById(R.id.button_cancel);
        mButtonSubmint = (Button) view.findViewById(R.id.button_submit);
        //set the content
        mTextViewContent = (TextView) view.findViewById(R.id.textView_content);
        mTextViewContent.setText(mContent);
        //set the on click submit button
        mButtonSubmint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication myApplication = (MyApplication) getActivity().getApplication();
                ArrayList<ToDialectQuestion> questionsList = myApplication.getQuestionList();
                for (int i = 0; i < questionsList.size(); i++) {
                    if (questionsList.get(i).getId() == mQuestionId) {
                        questionsList.get(i).setAnswer_num(1 + questionsList.get(i).getAnswer_num());
                    }
                }

                mListener.onFinishRecord();
            }
        });
        //set the on click cancel button
        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication myApplication = (MyApplication) getActivity().getApplication();
                ArrayList<ToDialectAnswer> answerList = myApplication.getAnswersList();
                answerList.remove(answerList.size() - 1);
                mIsRecording = false;
                mTextViewContent.setText(getResources().getString(R.string.btn_record_2));
                mButtonCancel.setVisibility(View.INVISIBLE);
                mButtonSubmint.setVisibility(View.INVISIBLE);
                mButtonRecord.setVisibility(View.VISIBLE);
                mButtonRecord.setText(getResources().getString(R.string.btn_record_0));
            }
        });
        //set the on click record button event
        mButtonRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsRecording) {//stop record
                    mIsRecording = false;
                    mRecorder.stop();
                    mRecorder.release();
                    mRecorder = null;

                    MyApplication myApplication = (MyApplication) getActivity().getApplication();
                    ArrayList<ToDialectAnswer> answerList = myApplication.getAnswersList();
                    ToDialectAnswer answer = new ToDialectAnswer();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    answer.setDate(sdf.format(new Date(System.currentTimeMillis())).toString());
                    answer.setVoice_time(5);
                    answer.setBest(true);
                    answer.setIcon(R.drawable.ic_menu_head2);
                    answer.setName("Jack");
                    answer.setId(answerList.size());
                    answer.setQuestionId(mQuestionId);
                    answer.setAudioPath(FileName);
                    answerList.add(answer);
                    mButtonRecord.setVisibility(View.INVISIBLE);
                    mButtonSubmint.setVisibility(View.VISIBLE);
                    mButtonCancel.setVisibility(View.VISIBLE);
                    mTextViewContent.setText(getResources().getString(R.string.btn_record_2));
                } else {//start record
                    mIsRecording = true;
                    mButtonRecord.setText(getResources().getString(R.string.btn_record_1));
                    mRecorder = new MediaRecorder();
                    mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mRecorder.setOutputFile(FileName);
                    mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    try {
                        mRecorder.prepare();
                    } catch (IOException e) {
                    }
                    mRecorder.start();
                }
            }
        });
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
        public void onFinishRecord();
    }


}
