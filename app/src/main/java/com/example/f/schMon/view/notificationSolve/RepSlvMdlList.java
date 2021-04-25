
package com.example.f.schMon.view.notificationSolve;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class RepSlvMdlList {

    @SerializedName("additionalOne")
    private Object mAdditionalOne;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("model")
    private List<RepSlvMdl> mRepSlvMdl;
    @SerializedName("module")
    private String mModule;
    @SerializedName("request_type")
    private String mRequestType;
    @SerializedName("success")
    private String mSuccess;
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

    public List<RepSlvMdl> getModel() {
        return mRepSlvMdl;
    }

    public void setModel(List<RepSlvMdl> repSlvMdl) {
        mRepSlvMdl = repSlvMdl;
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
        return "RepSlvMdlList{" +
                "mAdditionalOne=" + mAdditionalOne +
                ", mMessage='" + mMessage + '\'' +
                ", mRepSlvMdl=" + mRepSlvMdl +
                ", mModule='" + mModule + '\'' +
                ", mRequestType='" + mRequestType + '\'' +
                ", mSuccess='" + mSuccess + '\'' +
                ", mTotal='" + mTotal + '\'' +
                '}';
    }
}
