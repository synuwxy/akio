package com.synuwxy.akio.sample.buy.pay;

import com.synuwxy.akio.event.AkioHandleEvent;
import com.synuwxy.akio.sample.buy.model.UserModel;

/**
 * @author wxy
 */
public class PayCompleteEvent extends AkioHandleEvent {

    private UserModel userModel;

    PayCompleteEvent(Object source, UserModel userModel) {
        super(source);
        this.userModel = userModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }
}
