package com.synuwxy.akio.sample.buy.checkstock;

import com.synuwxy.akio.sample.model.GoodsModel;
import com.synuwxy.akio.sample.model.StoreModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author wxy
 */
@Service
@Slf4j
public class CheckStockService {

    private final ApplicationContext applicationContext;

    @Autowired
    public CheckStockService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void checkStock(GoodsModel goodsModel, StoreModel storeModel, int number) {
        log.info("检查库存");
        int stock = storeModel.getStock();
        log.info("[商店: {}] 库存剩余: {}", storeModel.getName(), stock);
        if (stock < number) {
            throw new RuntimeException("库存不足");
        }
        log.info("检查完成");
        applicationContext.publishEvent(new CheckStockCompleteEvent(this, goodsModel, storeModel, number));
    }
}
