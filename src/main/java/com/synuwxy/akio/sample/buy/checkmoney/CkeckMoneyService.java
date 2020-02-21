package com.synuwxy.akio.sample.buy.checkmoney;

import com.synuwxy.akio.sample.model.GoodsModel;
import com.synuwxy.akio.sample.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CkeckMoneyService {

    private final ApplicationContext applicationContext;

    public CkeckMoneyService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void checkMoney(GoodsModel goodsModel, UserModel userModel, int number) {
        log.info("检查余额");
        float price = goodsModel.getPrice();
        float total = price * number;
        log.info("[用户: {}] 余额: {}，需支付: {}", userModel.getName(), userModel.getMoney(), total);
        if (total > userModel.getMoney()) {
            throw new RuntimeException("余额不足");
        }
        log.info("检查完成");
        applicationContext.publishEvent(new CheckMoneyCompleteEvent(this, userModel, total));
    }
}
