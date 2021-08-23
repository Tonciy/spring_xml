package com.itheima.factory;

import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import java.io.ObjectInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author Zero
 * 用于创建Service的代理对象的工厂
 */
//@Component("beanFactory")
public class BeanFactory {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 获取service的代理对象
     * @return
     */
    @Bean("proxyAccountService")
    public IAccountService getAccountService(){
        return (IAccountService)Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(), new InvocationHandler() {
            /**
             * 给service中的每个方法添加事务的支持
             * @param proxy
             * @param method
             * @param args
             * @return
             * @throws Throwable
             */
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object res = null;
                try {
                    // 1. 开启事务
                    transactionManager.beginTransaction();
                    // 2. 执行操作
                    res = method.invoke(accountService, args);
                    // 3. 提交事务
                    transactionManager.commit();
                    // 4. 返回结果
                    return res;
                }catch (Exception e){
                    // 5. 回滚操作
                    transactionManager.rollback();
                    throw new RuntimeException(e);
                }finally {
                    // 6. 释放链接
                    transactionManager.release();
                }
            }
        });
    }

}
