package com.synuwxy.akio.sample.buy;

import com.synuwxy.akio.api.AbstractAkioListener;
import com.synuwxy.akio.sample.buy.checkmoney.CkeckMoneyService;
import com.synuwxy.akio.sample.buy.checkstock.CheckStockService;
import com.synuwxy.akio.sample.buy.model.GoodsModel;
import com.synuwxy.akio.sample.buy.model.StoreModel;
import com.synuwxy.akio.sample.buy.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author wxy
 */
@Service
@Slf4j
public class BuyService extends AbstractAkioListener<BuyGoodsEvent> {

    private final CheckStockService checkStockService;

    private final CkeckMoneyService ckeckMoneyService;

    @Autowired
    public BuyService(CheckStockService checkStockService, CkeckMoneyService ckeckMoneyService, ApplicationContext applicationContext) {
        super(applicationContext);
        this.checkStockService = checkStockService;
        this.ckeckMoneyService = ckeckMoneyService;
    }

    @Override
    public void handle(BuyGoodsEvent event) {
        UserModel userModel = event.getUserModel();
        GoodsModel goodsModel = event.getGoodsModel();
        StoreModel storeModel = event.getStoreModel();
        int number = event.getNumber();
        log.info("触发购买事件");
        log.info("[用户: {}] 向 [商店: {}] 购买了 [{}] 数量: {}", userModel.getName(), storeModel.getName(), goodsModel.getName(), number);

        checkStockService.checkStock(goodsModel, storeModel, number);
        ckeckMoneyService.checkMoney(goodsModel, userModel, number);
    }

    @Override
    public void offset(BuyGoodsEvent event) {
        log.info("购买事件回滚");
    }

    @Override
    public void complete(BuyGoodsEvent event) {

    }

}
