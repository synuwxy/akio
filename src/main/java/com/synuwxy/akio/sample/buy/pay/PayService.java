package com.synuwxy.akio.sample.buy.pay;

import com.synuwxy.akio.api.AbstractAkioListener;
import com.synuwxy.akio.sample.buy.checkmoney.CheckMoneyCompleteEvent;
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
public class PayService extends AbstractAkioListener<CheckMoneyCompleteEvent> {

    private final ApplicationContext applicationContext;

    @Autowired
    public PayService(ApplicationContext applicationContext) {
        super(applicationContext);
        this.applicationContext = applicationContext;
    }

    @Override
    public void handle(CheckMoneyCompleteEvent event) {
        log.info("支付中");
        UserModel userModel = event.getUserModel();
        float total = event.getTotal();
        float money = userModel.getMoney();
        float balance = money - total;
        if (balance < 0) {
            throw new RuntimeException("支付失败");
        }
        userModel.setMoney(balance);
        log.info("支付成功，[用户: {}] 余额: {}", userModel.getName(), balance);
    }

    @Override
    public void offset(CheckMoneyCompleteEvent event) {
        log.info("支付回滚");
        UserModel userModel = event.getUserModel();

//        尝试使用clone的方法避免对象引用造成的数据不一致
//        float total = event.getTotal();
//        float money = userModel.getMoney();
//        userModel.setMoney(money + total);

        log.info("回滚成功，[用户: {}] 余额: {}", userModel.getName(), userModel.getMoney());
    }

    @Override
    public void complete(CheckMoneyCompleteEvent event) {
        applicationContext.publishEvent(new PayCompleteEvent(this, event.getUserModel()));
    }
}
