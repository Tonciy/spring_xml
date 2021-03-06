package com.itheima.test;

import com.itheima.conf.SpringConfiguration;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Zero
 * @date 2021/8/13-21:07
 * 使用junit单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class) // 取代main方法，换成spring提供的
@ContextConfiguration(locations = "classpath:bean_xml.xml")  // 告知是基于xml配置的，
public class AccountServiceTest_Spring_xml {
//    @Autowired
//    ApplicationContext ac = null;
    @Autowired
    IAccountService as = null;

    @Test
    public void testFindAll(){
        List<Account> allAccount = as.findAllAccount();
        for (Account account : allAccount) {
            System.out.println(account);
        }
    }
    @Test
    public void testOne(){
        Account accountById = as.findAccountById(1);
        System.out.println(accountById);
    }
    @Test
    public void testUpdate(){
        Account account = new Account();
        account.setMoney(2345f);
        account.setName("咖啡机");
        account.setId(4);
        as.updateAccount(account);
    }
    @Test
    public void testSave(){
        Account account = new Account();
        account.setMoney(12345f);
        account.setName("形式111咖啡机");
        as.saveAccount(account);
    }
    @Test
    public void testDelete(){
        as.deleteAccount(4);
    }

}
