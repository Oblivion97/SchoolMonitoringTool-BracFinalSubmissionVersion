
package com.example.f.schMon.model.newAPI;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class AttndDashMdl {

    @SerializedName("attendance_percentage")
    private String mAttendancePercentage;
    @SerializedName("date")
    private String mDate;
    @SerializedName("school_code")
    private String mSchoolCode;
    @SerializedName("school_grade")
    private String mSchoolGrade;
    @SerializedName("school_id")
    private String mSchoolId;
    @SerializedName("school_name")
    private String mSchoolName;

    public String getAttendancePercentage() {
        return mAttendancePercentage;
    }

    public void setAttendancePercentage(String attendancePercentage) {
        mAttendancePercentage = attendancePercentage;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getSchoolCode() {
        return mSchoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        mSchoolCode = schoolCode;
    }

    public String getSchoolGrade() {
        return mSchoolGrade;
    }

    public void setSchoolGrade(String schoolGrade) {
        mSchoolGrade = schoolGrade;
    }

    public String getSchoolId() {
        return mSchoolId;
    }

    public void setSchoolId(String schoolId) {
        mSchoolId = schoolId;
    }

    public String getSchoolName() {
        return mSchoolName;
    }

    public void setSchoolName(String schoolName) {
        mSchoolName = schoolName;
    }


    @Override
    public String toString() {
        return "AttndDashMdl{" +
                "mAttendancePercentage='" + mAttendancePercentage + '\'' +
                ", mDate='" + mDate + '\'' +
                ", mSchoolCode='" + mSchoolCode + '\'' +
                ", mSchoolGrade='" + mSchoolGrade + '\'' +
                ", mSchoolId='" + mSchoolId + '\'' +
                ", mSchoolName='" + mSchoolName + '\'' +
                '}';
    }
}
