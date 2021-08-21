package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zero
 * 有事务控制的AccountService
 */
//@Service("accountService")
public class AccountServiceImpl_OLD implements IAccountService {
    @Autowired
    private IAccountDao accountDao;
    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setAccountDao(IAccountDao accountDao) {

        this.accountDao = accountDao;
    }

    public List<Account> findAllAccount() {
        try {
            // 1. 开启事务
            transactionManager.beginTransaction();
            // 2. 执行操作
            List<Account> accounts =  accountDao.findAllAccount();
            // 3. 提交事务
            transactionManager.commit();
            // 4. 返回结果
            return accounts;
        }catch (Exception e){
            // 5. 回滚操作
            transactionManager.rollback();
            throw new RuntimeException(e);
        }finally {
            // 6. 释放链接
            transactionManager.release();
        }

    }

    public Account findAccountById(Integer id)
    {
        try {
            // 1. 开启事务
            transactionManager.beginTransaction();
            // 2. 执行操作
            Account account =  accountDao.findAccountById(id);
            // 3. 提交事务
            transactionManager.commit();
            // 4. 返回结果
            return account;
        }catch (Exception e){
            // 5. 回滚操作
            transactionManager.rollback();
            throw new RuntimeException(e);
        }finally {
            // 6. 释放链接
            transactionManager.release();
        }

    }

    public void saveAccount(Account account)
    {
        try {
            // 1. 开启事务
            transactionManager.beginTransaction();
            // 2. 执行操作
            accountDao.saveAccount(account);
            // 3. 提交事务
            transactionManager.commit();
        }catch (Exception e){
            // 4. 回滚操作
            transactionManager.rollback();
            throw new RuntimeException(e);
        }finally {
            // 5. 释放链接
            transactionManager.release();
        }

    }

    public void updateAccount(Account account) {
        try {
            // 1. 开启事务
            transactionManager.beginTransaction();
            // 2. 执行操作
            accountDao.updateAccount(account);
            // 3. 提交事务
            transactionManager.commit();
        }catch (Exception e){
            // 4. 回滚操作
            transactionManager.rollback();
            throw new RuntimeException(e);
        }finally {
            // 5. 释放链接
            transactionManager.release();
        }
    }

    public void deleteAccount(Integer accountId) {
        try {
            // 1. 开启事务
            transactionManager.beginTransaction();
            // 2. 执行操作
            accountDao.deleteAccount(accountId);
            // 3. 提交事务
            transactionManager.commit();
        }catch (Exception e){
            // 4. 回滚操作
            transactionManager.rollback();
            throw new RuntimeException(e);
        }finally {
            // 5. 释放链接
            transactionManager.release();
        }
    }

    public void transfer(String sourceName, String targetName, Float money) {
        try {
            // 1. 开启事务
            transactionManager.beginTransaction();
            // 2. 执行操作
            // 2.1. 根据名称查询转出账户
            Account source = accountDao.findAccountByName(sourceName);
            // 2.2. 根据名称查询转入账户
            Account target = accountDao.findAccountByName(targetName);
            // 2.3. 转出账户减钱
            source.setMoney(source.getMoney() - money);
            // 2.4. 转入账户加钱
            target.setMoney(target.getMoney() + money);
            // 2.5. 更新转出账户
            accountDao.updateAccount(source);
            // 2.6. 更新转入账户
            accountDao.updateAccount(target);
            // 3. 提交事务
            transactionManager.commit();
        }catch (Exception e){
            // 4. 回滚操作
            transactionManager.rollback();
            throw new RuntimeException(e);
        }finally {
            // 5. 释放链接
            transactionManager.release();
        }

    }
}
