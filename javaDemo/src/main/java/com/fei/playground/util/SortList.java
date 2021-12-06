package com.fei.playground.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author  yuansq
 * @time 2017-12-28
 * @description list 排序用
 */
public class SortList<E> {
    /**
     * 比较数据字的
     * @param list
     * @param method
     * @param sort
     */
    public void sortNumber(List<E> list, final String method, final String sort) {
        Collections.sort(list, new Comparator() {
            public int compare(Object a, Object b) {
                int ret = 0;
                try {
                    Method m1 = ((E) a).getClass().getMethod(method, null);
                    Method m2 = ((E) b).getClass().getMethod(method, null);
                    if (sort != null && "desc".equals(sort))// 倒序
                        ret = (Integer)m2.invoke(((E) b), null)
                                -(Integer)m1.invoke(((E) a), null);
                    else
                        // 正序
                        ret = (Integer)m1.invoke(((E) a), null)
                                -(Integer)m2.invoke(((E) b), null);
                } catch (NoSuchMethodException ne) {
                    ne.printStackTrace();
                } catch (IllegalAccessException ie) {
                    ie.printStackTrace();
                } catch (InvocationTargetException it) {
                    it.printStackTrace();
                }
                return ret;
            }
        });
    }

    /**
     * 把目标字段转化为字符串排序
     * @param list
     * @param fieldName 字段名
     * @param sort "desc" || "asc"
     */
    public void sortByField(List<E> list, final String fieldName, final String sort) {
        Collections.sort(list, new Comparator() {
            public int compare(Object a, Object b) {
                int ret = 0;
                try {
                    boolean isASC = "ASC".equalsIgnoreCase(sort);
                    Field field = ((E) a).getClass().getDeclaredField(fieldName);
                    field.setAccessible(true);
                    Object o1 = field.get(a);
                    Object o2 = field.get(b);

                    if (o1 == null & o2 == null) {
                        return 0;
                    } else if (o1 != null && o2 == null) {
                        return isASC ? 1 : -1;
                    } else if (o1 == null && o2 != null) {
                        return isASC ? -1 : 1;
                    }

                    if (isASC) //正序
                        ret = o1.toString().compareTo(o2.toString());
                    else //倒序
                        ret = o2.toString().compareTo(o1.toString());
                } catch (IllegalAccessException | NoSuchFieldException ne) {
                    ne.printStackTrace();
                }
                return ret;
            }
        });
    }

    /**
     * 把目标字段转化为字符串排序 未测试 需要增加null检查
     * @param list
     * @param method 对比字段的getter函数命
     * @param sort "desc" || "asc"
     */
    public void sortByMethod(List<E> list, final String method, final String sort) {
        Collections.sort(list, new Comparator() {
            public int compare(Object a, Object b) {
                int ret = 0;
                try {
                    Method m1 = ((E) a).getClass().getMethod(method, null);
                    Method m2 = ((E) b).getClass().getMethod(method, null);
                    if ("desc".equalsIgnoreCase(sort))// 倒序
                        ret = m2.invoke(((E) b), null).toString()
                                .compareTo(m1.invoke(((E) a), null).toString());
                    else
                        // 正序
                        ret = m1.invoke(((E) a), null).toString()
                                .compareTo(m2.invoke(((E) b), null).toString());
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ne) {
                    ne.printStackTrace();
                }
                return ret;
            }
        });
    }

}
