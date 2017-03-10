
package com.pacewear.tws.phoneside.wallet.lnt;

import android.util.Log;

abstract class LntBaseHandler {
    protected LntBaseHandler nextHandler;
    protected LntSdkContext mContext;
    protected InvokeResult mInvokeResult;
    public static final String TAG = "Lnt";

    final class InvokeResult {
        private boolean bSuc;
        private String desc;
    }

    public LntBaseHandler(LntSdkContext context) {
        mContext = context;
        mInvokeResult = new InvokeResult();
    }

    protected final void logResult(boolean bSuc, String desc) {
        Log.d(TAG, "result:" + bSuc + "," + desc);
        mInvokeResult.bSuc = bSuc;
        mInvokeResult.desc = desc;
    }

    public void setNext(LntBaseHandler handler) {
        this.nextHandler = handler;
    }

    public abstract void invoke();

    protected final void next() {
        if (nextHandler != null) {
            nextHandler.invoke();
        } else {
            finish();
        }
    }

    protected final void finish() {
        ILntInvokeCallback callback = mContext.getCallback();
        if (callback != null) {
            callback.onResult(mInvokeResult.bSuc, mInvokeResult.desc);
        }
    }
}
