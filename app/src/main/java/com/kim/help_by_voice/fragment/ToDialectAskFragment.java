package com.kim.help_by_voice.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.kim.help_by_voice.Activitys.R;
import com.kim.help_by_voice.entity.MyApplication;
import com.kim.help_by_voice.entity.ToDialectQuestion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ToDialectAskFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ToDialectAskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToDialectAskFragment extends Fragment {
    // the fragment initialization parameters, e.
    private static final String ARG_PLACE = "place";
    private EditText mEditTextContent;
    private Button mButtonSubmit, mButtonCancel;

    private String mPlace;
    private int mId=0;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static ToDialectAskFragment newInstance(String place) {
        ToDialectAskFragment fragment = new ToDialectAskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PLACE, place);
        fragment.setArguments(args);
        return fragment;
    }

    public ToDialectAskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPlace = getArguments().getString(ARG_PLACE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_to_dialect_ask, container, false);
        mEditTextContent = (EditText) view.findViewById(R.id.editText_content);
        mButtonCancel = (Button) view.findViewById(R.id.button_cancel);
        mButtonSubmit = (Button) view.findViewById(R.id.button_submit);
        //set on click event
        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mEditTextContent.getText().toString();
                ToDialectQuestion question = new ToDialectQuestion();
                MyApplication myApplication = (MyApplication) getActivity().getApplication();
                ArrayList<ToDialectQuestion> questionList = myApplication.getQuestionList();
                question.setAnswer_num(0);
                question.setContent(content);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                question.setDate(sdf.format(new Date(System.currentTimeMillis())).toString());
                question.setIcon(R.drawable.ic_menu_head);
                mId = questionList.size();
                question.setId(questionList.size());
                question.setName("Kim");
                question.setPlace(mPlace);
                questionList.add(question);
                mListener.onButtonClicked();
            }
        });
        //set on click event
        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClicked();
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
        //use this method to set the button click event
        public void onButtonClicked();
    }

}
