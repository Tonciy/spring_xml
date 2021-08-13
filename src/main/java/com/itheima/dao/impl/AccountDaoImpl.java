package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * @author Zero
 * @date 2021/8/13-20:42
 */
public class AccountDaoImpl implements IAccountDao {
    private QueryRunner runner;

    public QueryRunner getRunner() {
        return runner;
    }

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    public List<Account> findAllAccount() {
        try {
            return runner.query("select * from account ", new BeanListHandler<Account>(Account.class));
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public Account findAccountById(Integer id) {
        try {
            return runner.query("select * from account where id = ? ", new BeanHandler<Account>(Account.class), id);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public void saveAccount(Account account) {
        try {
            runner.update("insert into account(name, money) values(?, ?) ",account.getName(),account.getMoney());
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public void updateAccount(Account account) {
        try {
            runner.update("update account set name = ?, money = ? where id = ? ",account.getName(),account.getMoney(),account.getId());
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public void deleteAccount(Integer accountId) {
        try {
            runner.update("delete from account where id = ? ", accountId);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
