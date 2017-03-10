
package com.pacewear.tws.phoneside.wallet.lnt;

import android.content.Context;

import com.pacewear.lntconnect.ConnectPaceFactoryImpl;

public class LntSdkContextWrapper {
    public static LntSdkContext newInstance(Context ctx, String packageName, String mac,
            String city, String usrId, boolean isReleaseEnv, ILntInvokeCallback callback) {
        LntSdkContext context = new LntSdkContext();
        context.setClient(ctx);
        context.setAppPkgName(packageName);
        context.setCallback(callback);
        context.setMacAddr(mac);
        context.setFactoryConnect(ConnectPaceFactoryImpl.getInstance(ctx));
        context.setCity(city);
        context.setUserId(usrId);
        context.setReleaseEnv(isReleaseEnv);
        return context;
    }
}
