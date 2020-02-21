package com.synuwxy.akio.sample.buy;

import com.synuwxy.akio.event.AkioHandleEvent;
import com.synuwxy.akio.sample.model.GoodsModel;
import com.synuwxy.akio.sample.model.StoreModel;
import com.synuwxy.akio.sample.model.UserModel;

/**
 * @author wxy
 */
public class BuyGoodsEvent extends AkioHandleEvent {

    private UserModel userModel;

    private GoodsModel goodsModel;

    private StoreModel storeModel;

    private int number;

    public BuyGoodsEvent(Object source, UserModel userModel, GoodsModel goodsModel, StoreModel storeModel, int number) {
        super(source);
        this.userModel = userModel;
        this.goodsModel = goodsModel;
        this.storeModel = storeModel;
        this.number = number;
    }

    UserModel getUserModel() {
        return userModel;
    }

    GoodsModel getGoodsModel() {
        return goodsModel;
    }

    StoreModel getStoreModel() {
        return storeModel;
    }

    int getNumber() {
        return number;
    }
}
