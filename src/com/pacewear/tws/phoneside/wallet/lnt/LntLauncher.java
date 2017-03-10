
package com.pacewear.tws.phoneside.wallet.lnt;

import com.lnt.rechargelibrary.impl.BalanceUtil;
import com.lnt.rechargelibrary.impl.RechargeUtil;

public class LntLauncher {

    public static void cardTopup(LntSdkContext context) {
        LntBaseHandler regist = new LntRegisty(context);
        LntBaseHandler recharge = new LntRecharge(context);
        LntBaseHandler cardquery = new LntCardQuery(context);

        regist.setNext(recharge);
        recharge.setNext(cardquery);

        regist.invoke();
    }

    public static void cardQuery(LntSdkContext context) {
        LntBaseHandler regist = new LntRegisty(context);
        LntBaseHandler cardquery = new LntCardQuery(context);

        regist.setNext(cardquery);

        regist.invoke();
    }

    public static void clear() {
        if (RechargeUtil.closeInterface != null) {
            RechargeUtil.closeInterface.onClose();
        }
        if (BalanceUtil.closeInterface != null) {
            BalanceUtil.closeInterface.onClose();
        }
    }
}
