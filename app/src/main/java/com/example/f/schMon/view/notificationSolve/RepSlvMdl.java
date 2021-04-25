
package com.example.f.schMon.view.notificationSolve;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class RepSlvMdl {

    @SerializedName("instituteName")
    private String mSchoolName;
    @SerializedName("gradeName")
    private  String mGrade;
    @SerializedName("answer")
    private Long mAnswer;
    @SerializedName("categoryId")
    private String mCategoryId;
    @SerializedName("categoryName")
    private String mCategoryName;
    @SerializedName("details")
    private String mDetails;
    @SerializedName("id")
    private String mId;
    @SerializedName("infrastructureQuestionId")
    private String mInfrastructureQuestionId;
    @SerializedName("instituteId")
    private String mInstituteId;
    @SerializedName("isProblem")
    private Long mIsProblem;
    @SerializedName("localId")
    private String mLocalId;
    @SerializedName("modifiedDate")
    private String mModifiedDate;
    @SerializedName("priority")
    private Long mPriority;
    @SerializedName("questionName")
    private String mQuestionName;
    @SerializedName("solveDate")
    private String mSolveDate;
    @SerializedName("submittedDate")
    private String mSubmittedDate;
    @SerializedName("targetDate")
    private String mTargetDate;
    @SerializedName("yesComment")
    private String mYesComment;




    public String getmSchoolName() {
        return mSchoolName;
    }

    public void setmSchoolName(String mSchoolName) {
        this.mSchoolName = mSchoolName;
    }

    public String getmGrade() {
        return mGrade;
    }

    public void setmGrade(String mGrade) {
        this.mGrade = mGrade;
    }

    public Long getAnswer() {
        return mAnswer;
    }

    public void setAnswer(Long answer) {
        mAnswer = answer;
    }

    public String getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(String categoryId) {
        mCategoryId = categoryId;
    }

    public String getCategoryName() {
        return mCategoryName;
    }

    public void setCategoryName(String categoryName) {
        mCategoryName = categoryName;
    }

    public String getDetails() {
        return mDetails;
    }

    public void setDetails(String details) {
        mDetails = details;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getInfrastructureQuestionId() {
        return mInfrastructureQuestionId;
    }

    public void setInfrastructureQuestionId(String infrastructureQuestionId) {
        mInfrastructureQuestionId = infrastructureQuestionId;
    }

    public String getInstituteId() {
        return mInstituteId;
    }

    public void setInstituteId(String instituteId) {
        mInstituteId = instituteId;
    }

    public Long getIsProblem() {
        return mIsProblem;
    }

    public void setIsProblem(Long isProblem) {
        mIsProblem = isProblem;
    }

    public String getLocalId() {
        return mLocalId;
    }

    public void setLocalId(String localId) {
        mLocalId = localId;
    }

    public String getModifiedDate() {
        return mModifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        mModifiedDate = modifiedDate;
    }

    public Long getPriority() {
        return mPriority;
    }

    public void setPriority(Long priority) {
        mPriority = priority;
    }

    public String getQuestionName() {
        return mQuestionName;
    }

    public void setQuestionName(String questionName) {
        mQuestionName = questionName;
    }

    public String getSolveDate() {
        return mSolveDate;
    }

    public void setSolveDate(String solveDate) {
        mSolveDate = solveDate;
    }

    public String getSubmittedDate() {
        return mSubmittedDate;
    }

    public void setSubmittedDate(String submittedDate) {
        mSubmittedDate = submittedDate;
    }

    public String getTargetDate() {
        return mTargetDate;
    }

    public void setTargetDate(String targetDate) {
        mTargetDate = targetDate;
    }

    public String getYesComment() {
        return mYesComment;
    }

    public void setYesComment(String yesComment) {
        mYesComment = yesComment;
    }


    @Override
    public String toString() {
        return "RepSlvMdl{" +
                "mSchoolName='" + mSchoolName + '\'' +
                ", mGrade='" + mGrade + '\'' +
                ", mAnswer=" + mAnswer +
                ", mCategoryId='" + mCategoryId + '\'' +
                ", mCategoryName='" + mCategoryName + '\'' +
                ", mDetails='" + mDetails + '\'' +
                ", mId='" + mId + '\'' +
                ", mInfrastructureQuestionId='" + mInfrastructureQuestionId + '\'' +
                ", mInstituteId='" + mInstituteId + '\'' +
                ", mIsProblem=" + mIsProblem +
                ", mLocalId='" + mLocalId + '\'' +
                ", mModifiedDate='" + mModifiedDate + '\'' +
                ", mPriority=" + mPriority +
                ", mQuestionName='" + mQuestionName + '\'' +
                ", mSolveDate='" + mSolveDate + '\'' +
                ", mSubmittedDate='" + mSubmittedDate + '\'' +
                ", mTargetDate='" + mTargetDate + '\'' +
                ", mYesComment='" + mYesComment + '\'' +
                '}';
    }
}
