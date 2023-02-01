package com.hujtb.commons.mysql.config;

import com.hujtb.commons.mysql.plugin.PagePlugin;
import com.hujtb.commons.mysql.plugin.SQLPlugin;
import com.hujtb.commons.mysql.property.SQLPluginConfigInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MySQL的自动装配
 */
@Configuration
@MapperScan({"com.hujtb.data.mapper"})
@EnableTransactionManagement
@EnableConfigurationProperties(SQLPluginConfigInfo.class) // 让SQLPluginConfigInfo这个类被容器扫描到
public class MySQLAutoConfiguration {

    // 注册MyBatis插件，如果属性值为false或没有配置该属性，则关闭该插件
    @Bean
    @ConditionalOnProperty(name = "hujtb.plugin.sql.enable", havingValue = "true", matchIfMissing = false)
    public SQLPlugin getSQLPlugin() {
        return new SQLPlugin();
    }

    @Bean
    public PagePlugin getPagePlugin() {
        return new PagePlugin();
    }
}
