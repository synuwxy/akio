package com.synuwxy.akio.sample.buy.checkstock;

import com.synuwxy.akio.event.AkioHandleEvent;
import com.synuwxy.akio.sample.model.GoodsModel;
import com.synuwxy.akio.sample.model.StoreModel;

/**
 * @author wxy
 */
public class CheckStockCompleteEvent extends AkioHandleEvent {

    private GoodsModel goodsModel;

    private StoreModel storeModel;

    private int number;

    CheckStockCompleteEvent(Object source, GoodsModel goodsModel, StoreModel storeModel, int number) {
        super(source);
        this.goodsModel = goodsModel;
        this.storeModel = storeModel;
        this.number = number;
    }

    public GoodsModel getGoodsModel() {
        return goodsModel;
    }

    public StoreModel getStoreModel() {
        return storeModel;
    }

    public int getNumber() {
        return number;
    }
}
