
package com.example.f.schMon.view.sync_mir.MdlSync;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class RepA {
    @Expose
    @SerializedName("answer")
    public int mAnswer;
    @Expose
    @SerializedName("categoryId")
    public String mCategoryId;
    @Expose
    @SerializedName("categoryName")
    public String mCategoryName;
    @Expose
    @SerializedName("details")
    public String mDetails;
    @Expose
    @SerializedName("id")
    public String mId;
    @Expose
    @SerializedName("infrastructureQuestionId")
    public String mInfrastructureQuestionId;
    @Expose
    @SerializedName("instituteId")
    public String mInstituteId;
    @Expose
    @SerializedName("isProblem")
    public String mIsProblem;
    @Expose
    @SerializedName("localId")
    public String mLocalId;
    @Expose
    @SerializedName("modifiedDate")
    public String mModifiedDate;
    @Expose
    @SerializedName("priority")
    public int mPriority;
    @Expose
    @SerializedName("questionName")
    public String mQuestionName;
    @Expose
    @SerializedName("solveDate")
    public String mSolveDate;
    @Expose
    @SerializedName("submittedDate")
    public String mSubmittedDate;// same in others models 'reportDate'
    @Expose
    @SerializedName("targetDate")
    public String mTargetDate;
    @Expose
    @SerializedName("yesComment")
    public String mYesComment;
    @SerializedName("instituteName")
    public String schName;
    @SerializedName("gradeName")
    public String grade;


    //======= extra field =======
    @Expose(serialize = false, deserialize = false)
    public transient String infrastructureQuestion;
    @Expose(serialize = false, deserialize = false)
    public transient String nfrastructureQuestionTitle;
    @Expose(serialize = false, deserialize = false)
    public transient String ques_category_id;
    @Expose(serialize = false, deserialize = false)
    public transient String category;
    @Expose(serialize = false, deserialize = false)
    public transient String synced;
    @Expose(serialize = false, deserialize = false)
    public transient String notify;
    @Expose(serialize = false, deserialize = false)
    public transient String server_ques;
    /*@Expose(serialize = false, deserialize = false)
    public transient String schName;
    @Expose(serialize = false, deserialize = false)
    public transient String grade;*/
    @Expose(serialize = false, deserialize = false)
    public transient String gradeID;
    @Expose(serialize = false, deserialize = false)
    public transient String schCode;


    //===========================================================================================

    public int getmAnswer() {
        return mAnswer;
    }

    public void setmAnswer(int mAnswer) {
        this.mAnswer = mAnswer;
    }

    public String getmCategoryId() {
        return mCategoryId;
    }

    public void setmCategoryId(String mCategoryId) {
        this.mCategoryId = mCategoryId;
    }

    public String getmCategoryName() {
        return mCategoryName;
    }

    public void setmCategoryName(String mCategoryName) {
        this.mCategoryName = mCategoryName;
    }

    public String getmDetails() {
        return mDetails;
    }

    public void setmDetails(String mDetails) {
        this.mDetails = mDetails;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmInfrastructureQuestionId() {
        return mInfrastructureQuestionId;
    }

    public void setmInfrastructureQuestionId(String mInfrastructureQuestionId) {
        this.mInfrastructureQuestionId = mInfrastructureQuestionId;
    }

    public String getmInstituteId() {
        return mInstituteId;
    }

    public void setmInstituteId(String mInstituteId) {
        this.mInstituteId = mInstituteId;
    }

    public String getmIsProblem() {
        return mIsProblem;
    }

    public void setmIsProblem(String mIsProblem) {
        this.mIsProblem = mIsProblem;
    }

    public String getmLocalId() {
        return mLocalId;
    }

    public void setmLocalId(String mLocalId) {
        this.mLocalId = mLocalId;
    }

    public String getmModifiedDate() {
        return mModifiedDate;
    }

    public void setmModifiedDate(String mModifiedDate) {
        this.mModifiedDate = mModifiedDate;
    }

    public int getmPriority() {
        return mPriority;
    }

    public void setmPriority(int mPriority) {
        this.mPriority = mPriority;
    }

    public String getmQuestionName() {
        return mQuestionName;
    }

    public void setmQuestionName(String mQuestionName) {
        this.mQuestionName = mQuestionName;
    }

    public String getmSolveDate() {
        return mSolveDate;
    }

    public void setmSolveDate(String mSolveDate) {
        this.mSolveDate = mSolveDate;
    }

    public String getmSubmittedDate() {
        return mSubmittedDate;
    }

    public void setmSubmittedDate(String mSubmittedDate) {
        this.mSubmittedDate = mSubmittedDate;
    }

    public String getmTargetDate() {
        return mTargetDate;
    }

    public void setmTargetDate(String mTargetDate) {
        this.mTargetDate = mTargetDate;
    }

    public String getmYesComment() {
        return mYesComment;
    }

    public void setmYesComment(String mYesComment) {
        this.mYesComment = mYesComment;
    }

    public String getInfrastructureQuestion() {
        return infrastructureQuestion;
    }

    public void setInfrastructureQuestion(String infrastructureQuestion) {
        this.infrastructureQuestion = infrastructureQuestion;
    }

    public String getNfrastructureQuestionTitle() {
        return nfrastructureQuestionTitle;
    }

    public void setNfrastructureQuestionTitle(String nfrastructureQuestionTitle) {
        this.nfrastructureQuestionTitle = nfrastructureQuestionTitle;
    }

    public String getQues_category_id() {
        return ques_category_id;
    }

    public void setQues_category_id(String ques_category_id) {
        this.ques_category_id = ques_category_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getServer_ques() {
        return server_ques;
    }

    public void setServer_ques(String server_ques) {
        this.server_ques = server_ques;
    }

    public String getSchName() {
        return schName;
    }

    public void setSchName(String schName) {
        this.schName = schName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGradeID() {
        return gradeID;
    }

    public void setGradeID(String gradeID) {
        this.gradeID = gradeID;
    }

    public String getSchCode() {
        return schCode;
    }

    public void setSchCode(String schCode) {
        this.schCode = schCode;
    }





    /*

    public int getAnswer() {
        return mAnswer;
    }

    public void setAnswer(int answer) {
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

    public String getIsProblem() {
        return mIsProblem;
    }

    public void setIsProblem(String isProblem) {
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

    public int getPriority() {
        return mPriority;
    }

    public void setPriority(int priority) {
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
*/


    @Override
    public String toString() {
        return "RepA{" +
                "mAnswer=" + mAnswer +
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
                ", infrastructureQuestion='" + infrastructureQuestion + '\'' +
                ", nfrastructureQuestionTitle='" + nfrastructureQuestionTitle + '\'' +
                ", ques_category_id='" + ques_category_id + '\'' +
                ", category='" + category + '\'' +
                ", synced='" + synced + '\'' +
                ", notify='" + notify + '\'' +
                ", server_ques='" + server_ques + '\'' +
                ", schName='" + schName + '\'' +
                ", grade='" + grade + '\'' +
                ", gradeID='" + gradeID + '\'' +
                ", schCode='" + schCode + '\'' +
                '}';
    }


    //=========================== methods  ====================================================================================================


    public void nullToBlankString() {
        if (mId == null)
            mId = "";
        if (mInstituteId == null)
            mInstituteId = "";
        if (mInfrastructureQuestionId == null)
            mInfrastructureQuestionId = "";
        if (mSubmittedDate == null)
            mSubmittedDate = "";
        if (mDetails == null)
            mDetails = "";
        if (mLocalId == null)
            mLocalId = "";
        if (mModifiedDate == null)
            mModifiedDate = "";
        if (mTargetDate == null)
            mTargetDate = "";
        if (mSolveDate == null)
            mSolveDate = "";
    }

}
