package com.itheima.conf;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import jdk.nashorn.internal.objects.annotations.Property;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/**
 * @author Zero
 * @date 2021/8/14-14:40
 * 该类是一个配置类，它的作用和bean.xml是一样的
 */
@Configuration // 指定当前类为配置类
@ComponentScan("com.itheima") // 指定要扫描的包
@Import(JdbcConfiguration.class) // 告知其他子配置类
@PropertySource("jdbc.properties") // 读取配置类
public class SpringConfiguration {

}
