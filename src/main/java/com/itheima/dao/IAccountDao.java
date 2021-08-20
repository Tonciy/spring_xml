package com.itheima.dao;

import com.itheima.domain.Account;

import java.util.List;

/**
 * @author Zero
 * @date 2021/8/13-20:42
 * 账户的持久层接口
 */
public interface IAccountDao {
    /**
     * 查询所有账户
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 根据id查询账户信息
     * @param id
     * @return
     */
    Account findAccountById(Integer id);

    /**
     * 保存账户
     * @param account
     */
    void  saveAccount(Account account);

    /**
     * 更新账户
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 根据id删除账户
     * @param accountId
     */
    void deleteAccount(Integer accountId);

    /**
     * 根据名称查询用户
     * @param name
     * @return
     */
    Account findAccountByName(String name);
}
