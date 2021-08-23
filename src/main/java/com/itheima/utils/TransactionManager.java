package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Zero
 * 和事务管理相关的工具类，它包含了开启事务，提交事务，回滚事务和释放链接
 */
@Component("transactionManager")
@Aspect
public class TransactionManager {
    @Autowired
    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    public void pt1(){}
    /**
     * 开启事务
     */
    public void beginTransaction(){
        try {
            System.out.println("开启事务");
            System.out.println(connectionUtils.getThreadConnection());
            connectionUtils.getThreadConnection().setAutoCommit(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 提交事务
     */
    public void commit(){
        try {
            System.out.println("提交事务");
            System.out.println(connectionUtils.getThreadConnection());
            connectionUtils.getThreadConnection().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 回滚事务
     */
    public void rollback(){
        try {
            System.out.println("回滚事务");
            connectionUtils.getThreadConnection().rollback();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 释放链接
     */
    public void release(){
        try {
            System.out.println("释放链接");
            connectionUtils.getThreadConnection().close();
            connectionUtils.removeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Around("pt1()")
    public Object arountAdvice(ProceedingJoinPoint pj){
        Object res = null;
        try {
            Object[] args = pj.getArgs();
            // 开启事务
            beginTransaction();
            // 执行切入点方法
            res = pj.proceed(args);
            // 提交事务
            commit();
            // 返回值
            return res;
        }catch (Throwable e){
            // 回滚事务
            rollback();
            throw new RuntimeException(e);
        }finally {
            // 释放资源
            release();
        }
    }
}
