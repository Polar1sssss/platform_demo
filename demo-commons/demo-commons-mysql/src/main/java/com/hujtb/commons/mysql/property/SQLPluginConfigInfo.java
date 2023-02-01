package com.hujtb.commons.mysql.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SQL插件的配置提示类，配置了该类之后yaml中会出现自动提示
 */
@Data
@ConfigurationProperties(prefix = "hujtb.plugin.sql")
public class SQLPluginConfigInfo {

    private boolean enable;
}
