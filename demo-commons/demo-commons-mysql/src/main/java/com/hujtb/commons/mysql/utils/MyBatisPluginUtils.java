package com.hujtb.commons.mysql.utils;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

/**
 * MyBatis插件工具类
 */
public class MyBatisPluginUtils {

    /**
     * 获取代理对象的内置目标对象
     *
     * @param target
     * @return
     */
    public static Object getNoProxyObject(Object target) {

        MetaObject metaObject = SystemMetaObject.forObject(target);
        // 如果对象中存在“h”属性，证明是代理对象，获取到持有的对象
        while (metaObject.hasGetter("h")) {
            target = metaObject.getValue("h.target");
            metaObject = SystemMetaObject.forObject(target);
        }
        return target;
    }

    /**
     * 返回SQL语句中主表from关键字下标
     *
     * @param sql
     * @param beginIndex 起始下标
     * @return
     */
    public static Integer getKeywordFromIndex(String sql, int beginIndex) {

        if (sql == null) return -1;

        // 从beginIndex开始，sql中第一个from关键字的下标
        int fromIndex = sql.indexOf(" from ", beginIndex);

        String sqlBefore = sql.substring(0, fromIndex);
        char[] sqlBeforeArray = sqlBefore.toCharArray();
        int count = 0;
        for (int i = 0; i < sqlBeforeArray.length; i++) {
            char c = sqlBeforeArray[i];
            if (c == '(') {
                count++;
            }
            if (c == ')') {
                count--;
            }
        }
        // 计数器结果为0 证明from前面的sql段中左右括号数量相等 即该from为主表from 否则递归执行该方法(其实位置改为fromIndex)
        if (count == 0) {
            return fromIndex;
        } else {
            return getKeywordFromIndex(sql, fromIndex + 6);
        }
    }

    public static void main(String[] args) {

        String sql = "select * , (select abc from sss) from student where id = (select id from class)";
        String countSql = "select count(*)" + sql.substring(MyBatisPluginUtils.getKeywordFromIndex(sql, 0));
        System.out.println(countSql);
    }
}
