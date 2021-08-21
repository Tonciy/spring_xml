package com.itheima.test;

import com.itheima.conf.SpringConfiguration;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Zero
 * @date 2021/8/13-21:07
 * 使用junit单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class) // 取代main方法，换成spring提供的
@ContextConfiguration(classes = SpringConfiguration.class)  // 告知是基于xml配置的，
public class AOP_Account_Spring_annotation {
//    @Autowired
//    ApplicationContext ac = null;
    @Autowired
    @Qualifier(value = "proxyAccountService")
    IAccountService as = null;

    @Test
    public void testTransfer(){
        as.transfer("aaa", "bbb", 100f);
    }

}
