
package com.pacewear.tws.phoneside.wallet.lnt;

import android.text.TextUtils;

import com.lnt.rechargelibrary.impl.AppRegisterCallbackInterface;
import com.lnt.rechargelibrary.impl.RegisterAppUtil;
import com.lnt.rechargelibrary.util.XXTea;

import java.util.concurrent.atomic.AtomicBoolean;

final class LntRegisty extends LntBaseHandler {
    private static AtomicBoolean mRegistSuc = new AtomicBoolean(false);

    public LntRegisty(LntSdkContext context) {
        super(context);
    }

    @Override
    public void invoke() {
        if (mRegistSuc.get()) {
            next();
            return;
        }
        String appMd5 = AppUtil.getSignMd5Str(mContext.getClient(), mContext.getAppPkgName());
        String sign = genSign(appMd5);
        if (TextUtils.isEmpty(sign)) {
            logResult(false, "gensign ,fail");
            finish();
            return;
        }
        RegisterAppUtil.registerApp(mContext.getClient(), mContext.getAppPkgName(),
                appMd5, sign, new AppRegisterCallbackInterface() {

                    @Override
                    public void onSuccess() {
                        mRegistSuc.set(true);
                        next();
                    }

                    @Override
                    public void onFail(String arg0) {
                        mRegistSuc.set(false);
                        logResult(false, arg0);
                        finish();
                    }
                });
    }

    private String genSign(String appMd5) {
        String str = RegisterAppUtil.sort(mContext.getAppPkgName() + appMd5);
        String sign = null;
        String appkey = AppUtil.getAppKey(mContext);
        try {
            String encryptStr = XXTea.encrypt(str, "UTF-8", appkey);
            byte[] bymd5 = RegisterAppUtil.encryptMD5(encryptStr.getBytes());
            String md5Str = RegisterAppUtil.toHex(bymd5);
            sign = md5Str.substring(md5Str.length() - 8, md5Str.length());
        } catch (Exception e) {
        }
        return sign;
    }

}
