
package com.pacewear.tws.phoneside.wallet.lnt;

import android.content.Context;

import com.lnt.connectfactorylibrary.ConnectFactoryImpl;

public class LntSdkContext {
    private boolean isReleaseEnv;
    private String mAppPkgName;
    private Context mClientCtx;
    private String mMacAddr;
    private String mUserId;
    private String mCity;
    private ILntInvokeCallback mCallback;
    private ConnectFactoryImpl mFactoryImpl;

    // 只能由本包之内的文件去构造
    LntSdkContext() {
        // TODO Auto-generated constructor stub
    }

    void setReleaseEnv(boolean isRelease) {
        isReleaseEnv = isRelease;
    }

    boolean isReleaseEnv() {
        return isReleaseEnv;
    }

    void setAppPkgName(String name) {
        mAppPkgName = name;
    }

    String getAppPkgName() {
        return mAppPkgName;
    }

    void setCallback(ILntInvokeCallback callback) {
        mCallback = callback;
    }

    ILntInvokeCallback getCallback() {
        return mCallback;
    }

    void setFactoryConnect(ConnectFactoryImpl impl) {
        mFactoryImpl = impl;
    }

    ConnectFactoryImpl getFactoryConnect() {
        return mFactoryImpl;
    }

    void setClient(Context context) {
        mClientCtx = context;
    }

    Context getClient() {
        return mClientCtx;
    }

    void setMacAddr(String addr) {
        mMacAddr = addr;
    }

    String getMacAddr() {
        return mMacAddr;
    }

    void setUserId(String userId) {
        mUserId = userId;
    }

    String getUserId() {
        return mUserId;
    }

    void setCity(String city) {
        mCity = city;
    }

    String getCity() {
        return mCity;
    }
}
