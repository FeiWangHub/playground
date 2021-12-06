package com.fei.playground.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *  model 工具类
 * @author ysq
 */
public class ModelUtil {
    /**
     * 根据字段自动 反射set model
     * @param bean
     * @param head_name
     * @param val
     * @return
     * @throws IllegalAccessException
     */
    public static Object reflexModel(Object bean, String head_name, String val) throws IllegalAccessException {
        if(val==null){
            return bean;
        }else {
            val=val.trim();
        }
        Class clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        String model_key = head_name.replaceAll("%", "").trim()
                .replaceAll(" ", "_")
                .replaceAll("/", "_")
                .replaceAll("\\.", "_")
                .replaceAll("-", "_")
                .replaceAll("__", "_")
                .replaceAll("__", "_");
        for (Field field : fields) {
            if (field.getName().equalsIgnoreCase(model_key)) {
                String type = field.getType().toString();
                field.setAccessible(true);
                if((field.getName().equals("monthly_pnl")||field.getName().equals("ytd_pnl")||field.getName().equals("my_exposure")||field.getName().equals("daily_pnl")) &&
                        (val.startsWith("# DivideByZeroException")||val.startsWith("# EvaluationException")||
                                val.startsWith("# FormatException"))){
                    field.set(bean,Double.parseDouble("0"));
                }else{
                    if (type.endsWith("int") || type.endsWith("Integer")) {
                        field.set(bean, (int)Double.parseDouble(val));
                    } else if (type.endsWith("Double") || type.endsWith("double")) {
                        val=val.replaceAll(",","");
                        Double f=null;
                        if(val.indexOf("%")>-1){
                            f=Double.parseDouble(val.replaceAll("%",""))*100;
                        }else {
                            try {
                                f=Double.parseDouble(val);
                            }catch (Exception e){
                                e.printStackTrace();
                                return null;
                            }
                        }
                        field.set(bean, f);
                    } else if (type.endsWith("float") || type.endsWith("Float")) {
                        val=val.replaceAll(",","");
                        Float f=null;
                        if(val.indexOf("%")>-1){
                            f=Float.parseFloat(val.replaceAll("%",""))*100;
                        }else {
                            f=Float.parseFloat(val);
                        }
                        field.set(bean, f);
                    } else if (type.endsWith("String")) {
                        field.set(bean, transactSQLInjection(val));
                    } else if (type.endsWith("Boolean") || type.endsWith("boolean")) {
                        field.set(bean, Boolean.parseBoolean(val));
                    } else if (type.endsWith("Long") || type.endsWith("long")) {
                        field.set(bean, Long.parseLong(val));
                    }else  if(type.endsWith("Timestamp")||type.endsWith("timestamp")){
                        field.set(bean, DateUtil.str2TimeStamp(val));
                    }
                    field.setAccessible(false);
                    break; }
            }
        }
        return bean;
    }
    public static String transactSQLInjection(String str)
    {
        str= str.replaceAll(".*([';]+|(--)+).*"," ");
        String badStr = "'exec|execute|insert|select|delete|update|count|drop|truncate";//过滤掉的sql关键字，可以手动添加
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            str.replaceAll(badStrs[i],badStrs[i]+"-");
        }
        return str;
    }
    /**
     * 剔除bean 属性为空串的
     * @param bean
     * @return
     * @throws IllegalAccessException
     */
    public static Object reflexModel(Object bean) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String type = field.getType().toString();
            // 获取属性的名字
            String name = field.getName();
            // 将属性的首字符大写，方便构造get，set方法
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            //获取get方法，用于赋值
            Method get=null,set=null;
            try {
                get= clazz.getMethod("get"+name);
            }catch (NoSuchMethodException e){
                continue;
            }
            Object value=   get.invoke(bean);
            field.setAccessible(true);
            //TODO 当前只考虑了String
            if (type.endsWith("String")) {
                set= clazz.getMethod("set"+name,String.class);
                String val=(String)value;
                set.invoke(bean,StringUtil.empty(val)?null:val);
            }
            field.setAccessible(false);
        }
        return bean;
    }
}
