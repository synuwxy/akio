package com.synuwxy.akio.sample.buy;

import com.synuwxy.akio.api.AbstractAkioListener;
import com.synuwxy.akio.sample.buy.pay.PayCompleteEvent;
import com.synuwxy.akio.sample.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author wxy
 */
@Slf4j
@Service
public class BuyCompleteService extends AbstractAkioListener<PayCompleteEvent> {


    public BuyCompleteService(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    public void handle(PayCompleteEvent event) {
        UserModel userModel = event.getUserModel();
        log.info("[用户: {}] 支付完成，写入数据", userModel.getName());
        throw new RuntimeException("意外错误");
    }

    @Override
    public void offset(PayCompleteEvent event) {
        log.info("写入数据回滚");
    }

    @Override
    public void complete(PayCompleteEvent event) {

    }
}
