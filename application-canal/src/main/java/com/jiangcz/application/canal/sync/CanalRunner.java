package com.jiangcz.application.canal.sync;

import com.jiangcz.application.common.util.Spring.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 类名称：CanalRunner<br>
 * 类描述：<br>
 * 创建时间：2019年08月27日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */
@Component
@Order
@Slf4j
public class CanalRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.debug(">>>> CanalApplication start successful, start connect canal server !!!<<<<<");
        CanalClient canalClient = SpringContextUtil.getBean(CanalClient.class);
        //canalClient.checkValid();
    }
}
