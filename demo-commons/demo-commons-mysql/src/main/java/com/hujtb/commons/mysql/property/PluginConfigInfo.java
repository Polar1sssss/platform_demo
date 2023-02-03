package com.hujtb.commons.mysql.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 插件的配置提示类，配置了该类之后yaml中会出现自动提示
 */
@Data
@ConfigurationProperties(prefix = "hujtb.plugin")
public class PluginConfigInfo {

    private Sql sql;
    private Page page;

    @Data
    private static class Sql {
        private boolean enable;
    }

    @Data
    private static class Page {
        private boolean enable;
    }
}
