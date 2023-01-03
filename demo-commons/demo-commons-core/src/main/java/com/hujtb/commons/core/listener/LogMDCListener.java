package com.hujtb.commons.core.listener;

import org.slf4j.MDC;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * 监听springboot环境参数准备好事件
 */
public class LogMDCListener implements GenericApplicationListener {

    private static final String APPLICATION_CONFIG_PROPERTIES = "configurationProperties";

    private static final String APP_NAME_KEY = "spring.application.name";

    /**
     * 表示监听的事件类型，如果返回true，则调用onApplicationEvent进行事件处理
     *
     * @param resolvableType 当前事件类型
     * @return
     */
    @Override
    public boolean supportsEventType(ResolvableType resolvableType) {

        return ApplicationEnvironmentPreparedEvent.class == resolvableType.getRawClass();
    }

    /**
     * 处理某个事件
     *
     * @param applicationEvent 当前监听到的事件对象
     */
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        // 将事件类型进行强制转换
        ApplicationEnvironmentPreparedEvent preparedEvent = (ApplicationEnvironmentPreparedEvent) applicationEvent;
        // 获得当前应用的参数环境
        ConfigurableEnvironment environment = preparedEvent.getEnvironment();
        // 获得参数源列表
        MutablePropertySources propertySources = environment.getPropertySources();
        // 获取固定名称的参数数据源
        PropertySource<?> propertySource = propertySources.get(APPLICATION_CONFIG_PROPERTIES);
        // 从该数据源获取相关参数
        String appName = (String) propertySource.getProperty(APP_NAME_KEY);

        // 将该微服务名称作为日志文件名称放到MDC容器中
        MDC.put("logName", appName);
        MDC.put("logPath", appName);
    }

    /**
     * 设置当前监听器的优先级，保证向MDC容器中设置logName这一操作先于log4j2.xml读取ctx:logName操作
     */
    public int getOrder() {

        return Ordered.HIGHEST_PRECEDENCE + 19;
    }
}
