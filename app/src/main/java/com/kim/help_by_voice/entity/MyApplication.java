package com.kim.help_by_voice.entity;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by SikentKim on 2015/3/31.
 */
public class MyApplication extends Application {
    private  ArrayList<ToDialectAnswer> answersList = new ArrayList<ToDialectAnswer>();
    private ArrayList<ToDialectQuestion> questionList = new ArrayList<ToDialectQuestion>();

    public ArrayList<ToDialectAnswer> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(ArrayList<ToDialectAnswer> answersList) {
        this.answersList = answersList;
    }

    public ArrayList<ToDialectQuestion> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(ArrayList<ToDialectQuestion> questionList) {
        this.questionList = questionList;
    }
}
