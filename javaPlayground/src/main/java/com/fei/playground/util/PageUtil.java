package com.fei.playground.util;

/**
 * Created by yuans on 2017-11-01.
 */
public class PageUtil {

    /**
     * 根据数据总量和每页数据量，算总页数
     */
    public static int calTotalPage(int totalCount, int pageSize) {
        return (int) (totalCount + pageSize - 1) / pageSize;
    }

    public static int calPageStartIndex(int pageNum, int pageSize) {
        return pageNum > 0 ? (pageNum - 1) * pageSize : 0;
    }

    public static int calPageEndIndex(int startIndex, int pageNum, int pageSize) {
        return startIndex + pageSize * (pageNum > 0 ? 1 : 0);
    }

}
