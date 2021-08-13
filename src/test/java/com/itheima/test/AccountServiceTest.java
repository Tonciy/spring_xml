package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author Zero
 * @date 2021/8/13-21:07
 * 使用junit单元测试
 */
public class AccountServiceTest {
    ApplicationContext ac = null;
    IAccountService as = null;
    @Before
    public  void  init(){
        ac = new ClassPathXmlApplicationContext("bean.xml");
        as = ac.getBean("accountService", IAccountService.class);
    }
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
        account.setName("形式咖啡机");
        as.saveAccount(account);
    }
    @Test
    public void testDelete(){
        as.deleteAccount(4);
    }

}
