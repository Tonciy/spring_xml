package com.itheima.service;

import com.itheima.domain.Account;

import java.util.List;

/**
 * @author Zero
 * @date 2021/8/13-20:37
 * 账户的服务层接口
 */
public interface IAccountService {

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
     * 根据转入账户名和转出账户名转账
     * @param sourceName 转入账户名
     * @param targetName 转出账户名
     * @param money       钱
     */
    void transfer(String sourceName, String targetName, Float money);
}
