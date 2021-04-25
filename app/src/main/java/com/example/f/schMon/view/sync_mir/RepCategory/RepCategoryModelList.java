
package com.example.f.schMon.view.sync_mir.RepCategory;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class RepCategoryModelList {

    @SerializedName("additionalOne")
    private Object mAdditionalOne;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("model")
    private List<RepCategoryModel> mRepCategoryModel;
    @SerializedName("module")
    private String mModule;
    @SerializedName("request_type")
    private String mRequestType;
    @SerializedName("success")
    private String mSuccess;
    @SerializedName("total")
    private Object mTotal;

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

    public List<RepCategoryModel> getModel() {
        return mRepCategoryModel;
    }

    public void setModel(List<RepCategoryModel> repCategoryModel) {
        mRepCategoryModel = repCategoryModel;
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

    public Object getTotal() {
        return mTotal;
    }

    public void setTotal(Object total) {
        mTotal = total;
    }


    @Override
    public String toString() {
        return "RepCategoryModelList{" +
                "mAdditionalOne=" + mAdditionalOne +
                ", mMessage='" + mMessage + '\'' +
                ", mRepCategoryModel=" + mRepCategoryModel +
                ", mModule='" + mModule + '\'' +
                ", mRequestType='" + mRequestType + '\'' +
                ", mSuccess='" + mSuccess + '\'' +
                ", mTotal=" + mTotal +
                '}';
    }
}
