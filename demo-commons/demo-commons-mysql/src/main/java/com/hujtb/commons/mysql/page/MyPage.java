package com.hujtb.commons.mysql.page;

/**
 * 分页的工具类
 */
public class MyPage {

    private static ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    /**
     * 获取分页信息
     * @return
     */
    public static Page getPage() {
        return pageThreadLocal.get();
    }

    /**
     * 设置分页信息
     * @param pageNum
     * @param pageSize
     */
    public static void setPage(Integer pageNum, Integer pageSize) {
        Page page = new Page();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        pageThreadLocal.set(page);
    }

    public static void clear() {
        pageThreadLocal.remove();
    }
}
