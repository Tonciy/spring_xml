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
 * @date 2021/8/13-20:41
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {

        this.accountDao = accountDao;
    }

    public List<Account> findAllAccount() {
        return  accountDao.findAllAccount();


    }

    public Account findAccountById(Integer id) {
        return  accountDao.findAccountById(id);
    }

    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    public void updateAccount(Account account) {
            accountDao.updateAccount(account);
    }

    public void deleteAccount(Integer accountId) {
            accountDao.deleteAccount(accountId);
    }

    public void transfer(String sourceName, String targetName, Float money) {
            // 2. 执行操作
            // 2.1. 根据名称查询转出账户
            Account source = accountDao.findAccountByName(sourceName);
            // 2.2. 根据名称查询转入账户
            Account target = accountDao.findAccountByName(targetName);
            // 2.3. 转出账户减钱
            source.setMoney(source.getMoney() - money);
//            测试出现异常事务是否有控制
//            int i = 2/0;
            // 2.4. 转入账户加钱
            target.setMoney(target.getMoney() + money);
            // 2.5. 更新转出账户
            accountDao.updateAccount(source);
            // 2.6. 更新转入账户
            accountDao.updateAccount(target);
    }
}
