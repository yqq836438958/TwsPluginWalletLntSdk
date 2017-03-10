
package com.pacewear.tws.phoneside.wallet.lnt;

import com.lnt.rechargelibrary.impl.RechargeCallbackInterface;
import com.lnt.rechargelibrary.impl.RechargeUtil;

final class LntRecharge extends LntBaseHandler {

    public LntRecharge(LntSdkContext context) {
        super(context);
    }

    @Override
    public void invoke() {
        // RechargeUtil.bleRecharge(context, typeCode, mac, userid, rechargeInterface);
        RechargeUtil.bleRechargeImpl(mContext.getClient(), RechargeUtil.PACEWEAR_LINK,
                mContext.getMacAddr(), mContext.getUserId(),
                mContext.getFactoryConnect(),
                new RechargeCallbackInterface() {

                    @Override
                    public void onSuccess(String arg0) {
                        logResult(true, arg0);
                        next();
                    }

                    @Override
                    public void onFail(String arg0) {
                        logResult(false, arg0);
                        finish();
                    }
                });
    }

}
