
package com.pacewear.tws.phoneside.wallet.lnt;

import com.lnt.rechargelibrary.impl.BalanceCallbackInterface;
import com.lnt.rechargelibrary.impl.BalanceUtil;

import org.json.JSONException;
import org.json.JSONObject;

final class LntCardQuery extends LntBaseHandler {

    public LntCardQuery(LntSdkContext context) {
        super(context);
    }

    @Override
    public void invoke() {
        BalanceUtil.queryBleBalanceImpl(null, mContext.getClient(), 0, mContext.getMacAddr(), null,
                true,
                mContext.getFactoryConnect(),
                new BalanceCallbackInterface() {

                    @Override
                    public void onFail(String arg0) {
                        logResult(false, arg0);
                        finish();
                    }

                    @Override
                    public void onSuccess(String arg0, String arg1, String arg2, String arg3) {
                        logResult(true, wrapCardQuery(arg0, arg1, arg2, arg3));
                        next();
                    }
                });
    }

    private String wrapCardQuery(String msg, String cardNmu, String cardBalance,
            String thresholdValue) {
        JSONObject json = new JSONObject();
        try {
            json.put(Constants.LNT_KEY_CARD_NUM, cardNmu);
            json.put(Constants.LNT_KEY_BALANCE, cardBalance);
            json.put(Constants.LNT_KEY_CARD_RECORD, thresholdValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}
