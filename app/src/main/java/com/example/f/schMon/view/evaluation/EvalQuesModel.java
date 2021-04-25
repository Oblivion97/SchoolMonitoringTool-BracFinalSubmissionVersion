/*
 * Copyright (c) MiR FeRdOuS
 */

package com.example.f.schMon.view.evaluation;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class EvalQuesModel {

    @SerializedName("server_id")
    private String server_id;
    @SerializedName("local_id")
    private String local_id;
    @SerializedName("ques")
    private String mQues;
    @SerializedName("answer")
    private String mAnswer;
    @SerializedName("active")
    private String active;
    @SerializedName("synced")
    private String mSynced;
    @SerializedName("time_lm")
    private String mTimeLm;
    @SerializedName("server_ques")
    private String serverQuestion;
    @SerializedName("grade")
    private String grade;
    @SerializedName("gradeID")
    private String gradeID;
    @SerializedName("date_asking")
    private String mDateAsking;
    @SerializedName("date_range_asking")
    private String mDateRangeAsking;
    @SerializedName("subject")
    String subject;
    @SerializedName("chapter")
    String chapter;

    public EvalQuesModel() {
    }

    public EvalQuesModel(String server_id, String mQues, String mAnswer, String active,
                         String mSynced, String mTimeLm, String serverQuestion, String grade) {
        this.server_id = server_id;
        this.mQues = mQues;
        this.mAnswer = mAnswer;
        this.active = active;
        this.mSynced = mSynced;
        this.mTimeLm = mTimeLm;
        this.serverQuestion = serverQuestion;
        this.grade = grade;
    }

    public EvalQuesModel(String server_id, String local_id, String gradeID, String mQues, String mAnswer, String active,
                         String mSynced, String mTimeLm, String serverQuestion, String grade,String subject,String chapter) {
        this.server_id = server_id;
        this.local_id = local_id;
        this.gradeID = gradeID;
        this.mQues = mQues;
        this.mAnswer = mAnswer;
        this.active = active;
        this.mSynced = mSynced;
        this.mTimeLm = mTimeLm;
        this.serverQuestion = serverQuestion;
        this.grade = grade;
        this.subject=subject;
        this.chapter=chapter;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getGrade() {
        return grade;
    }

    public String getGradeID() {
        return gradeID;
    }

    public void setGradeID(String gradeID) {
        this.gradeID = gradeID;
    }

    public String getLocal_id() {
        return local_id;
    }

    public void setLocal_id(String local_id) {
        this.local_id = local_id;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getServerQuestion() {
        return serverQuestion;
    }

    public void setServerQuestion(String serverQuestion) {
        this.serverQuestion = serverQuestion;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }

    public String getDateAsking() {
        return mDateAsking;
    }

    public void setDateAsking(String dateAsking) {
        mDateAsking = dateAsking;
    }

    public String getDateRangeAsking() {
        return mDateRangeAsking;
    }

    public void setDateRangeAsking(String dateRangeAsking) {
        mDateRangeAsking = dateRangeAsking;
    }

    public String getQues() {
        return mQues;
    }

    public void setQues(String ques) {
        mQues = ques;
    }

    public String getServer_id() {
        return server_id;
    }

    public void setServer_id(String quesID) {
        server_id = quesID;
    }

    public String getSynced() {
        return mSynced;
    }

    public void setSynced(String synced) {
        mSynced = synced;
    }

    public String getTimeLm() {
        return mTimeLm;
    }

    public void setTimeLm(String timeLm) {
        mTimeLm = timeLm;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }
//__________________________-


    @Override
    public String toString() {
        return "EvalQuesModel{" +
                "\nserver_id='" + server_id + '\'' +
                "\nlocal_id='" + local_id + '\'' +
                ", \nQues='" + mQues + '\'' +
                ", \nAnswer='" + mAnswer + '\'' +
                ", \nactive='" + active + '\'' +
                ", \nSynced='" + mSynced + '\'' +
                ", \nTimeLm='" + mTimeLm + '\'' +
                ", \nserverQuestion='" + serverQuestion + '\'' +
                ", \ngrade='" + grade + '\'' +
                ", \ngradeID='" + gradeID + '\'' +
                ", \nDateAsking='" + mDateAsking + '\'' +
                ", \nDateRangeAsking='" + mDateRangeAsking + '\'' +
                ", \nsubject='" + subject + '\'' +
                ", \nchapter='" + chapter + '\'' +
                '}' + "\n\n";
    }
}
