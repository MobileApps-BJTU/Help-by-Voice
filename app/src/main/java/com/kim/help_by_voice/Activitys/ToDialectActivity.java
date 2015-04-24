package com.kim.help_by_voice.Activitys;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.kim.help_by_voice.entity.MyApplication;
import com.kim.help_by_voice.entity.ToDialectQuestion;
import com.kim.help_by_voice.fragment.AnswerToDialectFragement;
import com.kim.help_by_voice.fragment.ToDialectAnswerFragment;


public class ToDialectActivity extends Activity implements ToDialectAnswerFragment.OnFragmentInteractionListener, AnswerToDialectFragement.OnFragmentInteractionListener {
    private int mQuestionId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_dialect);
        Intent intent = getIntent();
        mQuestionId = intent.getIntExtra("id", 0);
        MyApplication myApplication = (MyApplication) getApplication();
        ToDialectQuestion question = myApplication.getQuestionList().get(mQuestionId);
        if (savedInstanceState == null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.container, ToDialectAnswerFragment.newInstance(question.getName(), question.getPlace(), question.getContent(), question.getDate(), question.getAnswer_num(), mQuestionId, question.getIcon()));
            ft.commit();
        }
    }


    @Override
    public void onAnswerButtonClicked(String content, int id) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, AnswerToDialectFragement.newInstance(content, id));
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onFinishRecord() {
        MyApplication myApplication = (MyApplication) getApplication();
        ToDialectQuestion question = myApplication.getQuestionList().get(mQuestionId);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, ToDialectAnswerFragment.newInstance(question.getName(), question.getPlace(), question.getContent(), question.getDate(), question.getAnswer_num(), mQuestionId, question.getIcon()));
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
