package com.hujtb.commons.mysql.plugin;

import com.hujtb.commons.mysql.utils.MyBatisPluginUtils;
import com.hujtb.data.page.MyPage;
import com.hujtb.data.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
@Slf4j
public class PagePlugin implements Interceptor {

    /**
     * 当前拦截的是MyBatis的StatementHandler#prepare方法
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = (StatementHandler) MyBatisPluginUtils.getNoProxyObject(invocation.getTarget());
        String sql = statementHandler.getBoundSql().getSql().toLowerCase().replaceAll("\n", "").replaceAll(" ", "");
        // 判断当前是否是查询sql
        if (!sql.startsWith("select")) {
            return invocation.proceed();
        }

        // 根据请求上下文获取分页对象Page，如果能够获取到，则需要分页，使用ThreadLocal
        Page page = MyPage.getPage();
        if (page == null) {
            return invocation.proceed();
        }

        // 开始分页
        log.info("[page - info] 开始分页：{}", sql);
        // 计算出总条数
        Integer count = getCount(invocation, sql, statementHandler);
        // 相关参数设置
        if (page.getPageSize() == null) page.setPageSize(10);
        page.setPageCount(count);
        page.setPageTotal(page.getPageCount() % page.getPageSize() == 0 ? page.getPageCount() / page.getPageSize() : page.getPageCount() / page.getPageSize() + 1);
        // 临界值设置
        if (page.getPageNum() < 1) page.setPageNum(1);
        if (page.getPageNum() > page.getPageTotal() && page.getPageTotal() > 0) page.setPageNum(page.getPageTotal());

        sql += " limit " + (page.getPageNum() - 1) * page.getPageSize() + ", " + page.getPageSize();
        log.info("[page - info] 分页SQL：{}", sql);
        // 将原有sql替换为分页sql，并放行
        BoundSql boundSql = statementHandler.getBoundSql();
        MetaObject metaObject = SystemMetaObject.forObject(boundSql);
        metaObject.setValue("sql", sql);
        log.info("[page - info] 分页完成！");
        return invocation.proceed();
    }

    /**
     * 计算查询总条数
     *
     * @return
     */
    private Integer getCount(Invocation invocation, String sql, StatementHandler statementHandler) throws SQLException {

        String fromSql = sql.substring(sql.indexOf(" from "));
        String countSql = "select count(*) as count " + fromSql;
        log.info("[page - info] 生成记录总条数sql：{}", countSql);
        // 获取数据库连接
        Connection connection = (Connection) invocation.getArgs()[0];
        ResultSet rs = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            // 自动设置参数
            statementHandler.parameterize(ps);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                log.info("[page - info] 获取到查询记录总条数：{}", count);
                return count;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return 0;
    }
}
