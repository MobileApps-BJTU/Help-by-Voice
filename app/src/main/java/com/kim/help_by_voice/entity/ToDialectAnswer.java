package com.kim.help_by_voice.entity;

/**
 * Created by SikentKim on 2015/3/30.
 */
public class ToDialectAnswer {
    private int questionId, id;
    private String audioPath;
    private String date;
    private int icon;
    private String name;
    private boolean isBest=false;
    private double voice_time=0;

    public double getVoice_time() {
        return voice_time;
    }

    public void setVoice_time(double voice_time) {
        this.voice_time = voice_time;
    }

    public boolean isBest() {
        return isBest;
    }

    public void setBest(boolean isBest) {
        this.isBest = isBest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }


    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }
}
