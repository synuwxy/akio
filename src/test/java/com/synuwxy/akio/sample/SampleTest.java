package com.synuwxy.akio.sample;

import com.synuwxy.akio.sample.buy.BuyGoodsEvent;
import com.synuwxy.akio.sample.common.UUIDUtil;
import com.synuwxy.akio.sample.buy.model.GoodsModel;
import com.synuwxy.akio.sample.buy.model.StoreModel;
import com.synuwxy.akio.sample.buy.model.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SampleTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void BuyTest() {
        UserModel userModel = new UserModel(UUIDUtil.generatorId(), "张三", 0, 300);
        GoodsModel goodsModel = new GoodsModel(UUIDUtil.generatorId(), "口罩", 5);
        StoreModel storeModel = new StoreModel(UUIDUtil.generatorId(), "药店", 100);
        int number = 10;

        applicationContext.publishEvent(new BuyGoodsEvent(this, userModel, goodsModel, storeModel, number));
    }
}
