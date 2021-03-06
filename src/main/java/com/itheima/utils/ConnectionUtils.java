package com.itheima.utils;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author Zero
 * 连接的工具类，它用于从数据源中获取一个连接，并且实现和线程的绑定
 */
@Component("connectionUtils")
public class ConnectionUtils {
    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
    @Autowired
    private DataSource ds = null;

    public void setDs(DataSource ds) {
        this.ds = ds;
    }

    /**
     * 获取当前线程上的数据库连接对象
     * @return
     */
    public Connection getThreadConnection(){
        try {
            // 1. 从当前ThreadLocal上获取
            Connection conn = tl.get();
            // 2. 判断当前线程上是否有链接
            if(conn == null){
                // 3. 从数据源中获取一个链接，并且存入ThreadLocal
                conn = ds.getConnection();
                tl.set(conn);
            }
            // 4. 返回当前线程上的链接
            return conn;
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    /**
     * 将线程和连接对象解绑
     */
    public void  removeConnection(){
        tl.remove();
    }
}
