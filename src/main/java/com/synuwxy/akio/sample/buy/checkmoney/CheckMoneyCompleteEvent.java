package com.synuwxy.akio.sample.buy.checkmoney;

import com.synuwxy.akio.event.AkioHandleEvent;
import com.synuwxy.akio.sample.buy.model.UserModel;

/**
 * @author wxy
 */
public class CheckMoneyCompleteEvent extends AkioHandleEvent {

    private UserModel userModel;

    private float total;

    CheckMoneyCompleteEvent(Object source, UserModel userModel, float total) {
        super(source);
        this.userModel = userModel;
        this.total = total;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public float getTotal() {
        return total;
    }
}
