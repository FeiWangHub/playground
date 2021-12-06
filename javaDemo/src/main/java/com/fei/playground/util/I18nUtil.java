package com.fei.playground.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * 国际化翻译util
 */
@Component
public class I18nUtil {

    private static MessageSource i18n;

    public I18nUtil(MessageSource messageSource){
        this.i18n = messageSource;
    }

    /**
     * 获取单个国际化翻译值（可以自定义locale）
     * @param msgKey resources\i18n\messages_zh_CN.properties等文件中映射的key
     * @param locale 目前支持Locale.SIMPLIFIED_CHINESE, Locale.US
     */
    public static String get(String msgKey, Locale locale) {
        if (locale == null) {
            locale = LocaleContextHolder.getLocale();
        }
        try {
            return i18n.getMessage(msgKey, null, locale);
        } catch (Exception e) {
            return msgKey;
        }
    }

    /**
     * 获取国际化翻译（使用当前session中的locale，取自http header的Accept-Language字段）
     * @param msgKey
     * @return
     */
    public static String get(String msgKey) {
        Locale locale = LocaleContextHolder.getLocale();
        return get(msgKey, locale);
    }
}
