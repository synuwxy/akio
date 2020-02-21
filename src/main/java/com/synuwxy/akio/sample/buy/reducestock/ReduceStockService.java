package com.synuwxy.akio.sample.buy.reducestock;

import com.synuwxy.akio.api.AbstractAkioListener;
import com.synuwxy.akio.sample.buy.checkstock.CheckStockCompleteEvent;
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
public class ReduceStockService extends AbstractAkioListener<CheckStockCompleteEvent> {

    @Autowired
    public ReduceStockService(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    public void handle(CheckStockCompleteEvent event) {
        log.info("减库存中");
        StoreModel storeModel = event.getStoreModel();
        int number = event.getNumber();
        int stock = storeModel.getStock();
        int surplus = stock - number;
        if (surplus < 0) {
            throw new RuntimeException("库存不足");
        }
        storeModel.setStock(surplus);
        log.info("减库存成功，[商店: {}] 剩余库存: {}", storeModel.getName(), surplus);
    }

    @Override
    public void offset(CheckStockCompleteEvent event) {
        log.info("库存回滚");
        StoreModel storeModel = event.getStoreModel();
        int number = event.getNumber();
        int stock = storeModel.getStock();

        storeModel.setStock(number + stock);
        log.info("回滚成功，[商店: {}] 剩余库存: {}", storeModel.getName(), number + stock);
    }

    @Override
    public void complete(CheckStockCompleteEvent event) {

    }
}
