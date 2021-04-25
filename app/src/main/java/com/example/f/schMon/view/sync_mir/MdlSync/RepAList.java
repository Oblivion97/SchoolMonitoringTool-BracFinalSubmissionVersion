
package com.example.f.schMon.view.sync_mir.MdlSync;

import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class RepAList {

    @Expose
    @SerializedName("additionalOne")
    private Object mAdditionalOne;
    @Expose
    @SerializedName("message")
    private String mMessage;
    @Expose
    @SerializedName("model")
    private List<RepA> mA;
    @Expose
    @SerializedName("module")
    private String mModule;
    @Expose
    @SerializedName("request_type")
    private String mRequestType;
    @Expose
    @SerializedName("success")
    private String mSuccess;
    @Expose
    @SerializedName("total")
    private String mTotal;

    public Object getAdditionalOne() {
        return mAdditionalOne;
    }

    public void setAdditionalOne(Object additionalOne) {
        mAdditionalOne = additionalOne;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public List<RepA> getModel() {
        return mA;
    }

    public void setModel(List<RepA> a) {
        mA = a;
    }

    public String getModule() {
        return mModule;
    }

    public void setModule(String module) {
        mModule = module;
    }

    public String getRequestType() {
        return mRequestType;
    }

    public void setRequestType(String requestType) {
        mRequestType = requestType;
    }

    public String getSuccess() {
        return mSuccess;
    }

    public void setSuccess(String success) {
        mSuccess = success;
    }

    public String getTotal() {
        return mTotal;
    }

    public void setTotal(String total) {
        mTotal = total;
    }

    @Override
    public String toString() {
        return "RepAList{" +
                "mAdditionalOne=" + mAdditionalOne +
                ", mMessage='" + mMessage + '\'' +
                ", mA=" + mA +
                ", mModule='" + mModule + '\'' +
                ", mRequestType='" + mRequestType + '\'' +
                ", mSuccess='" + mSuccess + '\'' +
                ", mTotal='" + mTotal + '\'' +
                '}';
    }
}
