package com.hujtb.commons.mysql.plugin;

import com.hujtb.commons.mysql.utils.MyBatisPluginUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Statement;


/**
 * import java.sql.Statement;
 * SQL监控插件
 *
 * @Signature参数说明：type 指定拦截器增强的内置对象类型；method 指定增强的内置对象中的方法；args 参数类型
 */
@Slf4j
@Intercepts({
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class, ResultHandler.class}),
})
public class SQLPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = (StatementHandler) MyBatisPluginUtils.getNoProxyObject(invocation.getTarget());
        // 记录执行的SQL
        String sql = statementHandler.getBoundSql().getSql().toLowerCase().replaceAll("\n", "").replaceAll(" ", "");
        log.info("[SQL - EXEC] 执行的SQL语句：{}", sql);
        // 记录SQL耗时
        Long beginTime = System.currentTimeMillis();
        // 放行SQL
        Object result = invocation.proceed();
        Long endTime = System.currentTimeMillis();
        BigDecimal duration = (BigDecimal.valueOf(endTime).subtract(BigDecimal.valueOf(beginTime))).divide(BigDecimal.valueOf(1000)).setScale(6, RoundingMode.DOWN);
        // 计算耗时
        log.info("[SQL - EXEC] SQL耗时：{}s", duration);
        return result;
    }
}
