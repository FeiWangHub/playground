package com.fei.playground.util;

//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import net.sourceforge.pinyin4j.PinyinHelper;
//import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
//import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
//import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yuans on 2017-10-17.
 */
public class StringUtil {

    public static void main(String[] args) {
        System.out.println(switchLowerUpper('a'));
    }

    /**
     * 大写字母变小写，小写变大写
     */
    public static char switchLowerUpper(char c) {
        return (char) (c ^ 32);
    }

    /**
     * 首字母变小写
     */
    public static String firstCharToLowerCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] += ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    /**
     * 首字母变大写
     */
    public static String firstCharToUpperCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'a' && firstChar <= 'z') {
            char[] arr = str.toCharArray();
            arr[0] -= ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    public static boolean empty(final String str) {
        return (str == null) || (str.length() == 0);
    }

    public static boolean notEmpty(final String str) {
        return !empty(str);
    }

    public static boolean blank(final String str) {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0))
            return true;
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean notBlank(final String str) {
        return !blank(str);
    }

    public static boolean allEmpty(String... strings) {
        if (strings == null) {
            return true;
        }
        for (String str : strings) {
            if (notEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasEmpty(String... strings) {
        if (strings == null) {
            return true;
        }
        for (String str : strings) {
            if (empty(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * checkValue为 null 或者为 "" 时返回 defaultValue
     */
    public static String empty(String checkValue, String defaultValue) {
        return empty(checkValue) ? defaultValue : checkValue;
    }

    /**
     * checkValue为 null 或者为 "" 时返回 defaultValue
     */
    public static Double empty(Double checkValue, Double defaultValue) {
        return checkValue == null ? defaultValue : checkValue;
    }

    /**
     * 字符串不为 null 而且不为 "" 并且等于other
     */
    public static boolean notEmptyAndEqOther(String str, String other) {
        if (empty(str)) {
            return false;
        }
        return str.equals(other);
    }

    /**
     * 字符串不为 null 而且不为 "" 并且不等于other
     */
    public static boolean notEmptyAndNotEqOther(String str, String... other) {
        if (empty(str)) {
            return false;
        }
        for (int i = 0; i < other.length; i++) {
            if (str.equals(other[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串不等于other
     */
    public static boolean notEqOther(String str, String... other) {
        for (int i = 0; i < other.length; i++) {
            if (other[i].equals(str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean notEmpty(String... strings) {
        if (strings == null) {
            return false;
        }
        for (String str : strings) {
            if (str == null || "".equals(str.trim())) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(String value, String equals) {
        if (allEmpty(value, equals)) {
            return true;
        }
        return value.equals(equals);
    }

    /**
     * checkValue为 null   时返回 0
     */
    public static Double empty(Double checkValue) {
        return checkValue == null ? 0d : checkValue;
    }

    public static boolean notEquals(String value, String equals) {
        return !equals(value, equals);
    }

    public static String[] split(String content, String separatorChars) {
        return splitWorker(content, separatorChars, -1, false);
    }

    public static String[] split(String str, String separatorChars, int max) {
        return splitWorker(str, separatorChars, max, false);
    }

    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    private static String[] splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens) {
        // Performance tuned for 2.0 (JDK1.4)
        // Direct code is quicker than StringTokenizer.
        // Also, StringTokenizer uses isSpace() not isWhitespace()

        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return EMPTY_STRING_ARRAY;
        }
        List<String> list = new ArrayList<String>();
        int sizePlus1 = 1;
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        if (separatorChars == null) {
            // Null separator means use whitespace
            while (i < len) {
                if (Character.isWhitespace(str.charAt(i))) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else if (separatorChars.length() == 1) {
            // Optimise 1 character case
            char sep = separatorChars.charAt(0);
            while (i < len) {
                if (str.charAt(i) == sep) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else {
            // standard case
            while (i < len) {
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        }
        if (match || (preserveAllTokens && lastMatch)) {
            list.add(str.substring(start, i));
        }
        return (String[]) list.toArray(EMPTY_STRING_ARRAY);
    }

    public static String escapeXML(String str) {
        if (str == null)
            return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            switch (c) {
                case '\u00FF':
                case '\u0024':
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '\"':
                    sb.append("&quot;");
                    break;
                case '\'':
                    sb.append("&apos;");
                    break;
                default:
                    if (c >= '\u0000' && c <= '\u001F')
                        break;
                    if (c >= '\uE000' && c <= '\uF8FF')
                        break;
                    if (c >= '\uFFF0' && c <= '\uFFFF')
                        break;
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * 将字符串中特定模式的字符转换成map中对应的值
     *
     * @param s   需要转换的字符串
     * @param map 转换所需的键值对集合
     * @return 转换后的字符串
     */
    public static String replace(String s, Map<String, Object> map) {
        StringBuilder ret = new StringBuilder((int) (s.length() * 1.5));
        int cursor = 0;
        for (int start, end; (start = s.indexOf("${", cursor)) != -1 && (end = s.indexOf("}", start)) != -1; ) {
            ret.append(s.substring(cursor, start)).append(map.get(s.substring(start + 2, end)));
            cursor = end + 1;
        }
        ret.append(s.substring(cursor, s.length()));
        return ret.toString();
    }

    public static String replace(String s, Object... objs) {
        if (objs == null || objs.length == 0)
            return s;
        if (s.indexOf("{}") == -1)
            return s;
        StringBuilder ret = new StringBuilder((int) (s.length() * 1.5));
        int cursor = 0;
        int index = 0;
        for (int start; (start = s.indexOf("{}", cursor)) != -1; ) {
            ret.append(s.substring(cursor, start));
            if (index < objs.length)
                ret.append(objs[index]);
            else
                ret.append("{}");
            cursor = start + 2;
            index++;
        }
        ret.append(s.substring(cursor, s.length()));
        return ret.toString();
    }

    /**
     * 字符串格式化工具,参数必须以{0}之类的样式标示出来.大括号中的数字从0开始。
     *
     * @param source 源字符串
     * @param params 需要替换的参数列表,写入时会调用每个参数的toString().
     * @return 替换完成的字符串。如果原始字符串为空或者参数为空那么将直接返回原始字符串。
     */
    public static String replaceArgs(String source, Object... params) {
        if (params == null || params.length == 0 || source == null || source.isEmpty()) {
            return source;
        }
        StringBuilder buff = new StringBuilder(source);
        StringBuilder temp = new StringBuilder();
        int startIndex = 0;
        int endIndex = 0;
        String param = null;
        for (int count = 0; count < params.length; count++) {
            if (params[count] == null) {
                param = null;
            } else {
                param = params[count].toString();
            }

            temp.delete(0, temp.length());
            temp.append("{");
            temp.append(count);
            temp.append("}");
            while (true) {
                startIndex = buff.indexOf(temp.toString(), endIndex);
                if (startIndex == -1) {
                    break;
                }
                endIndex = startIndex + temp.length();

                buff.replace(startIndex, endIndex, param == null ? "" : param);
            }
            startIndex = 0;
            endIndex = 0;
        }
        return buff.toString();
    }

    public static String substringBefore(final String s, final String separator) {
        if (empty(s) || separator == null) {
            return s;
        }
        if (separator.isEmpty()) {
            return "";
        }
        final int pos = s.indexOf(separator);
        if (pos < 0) {
            return s;
        }
        return s.substring(0, pos);
    }

    public static String substringBetween(final String str, final String open, final String close) {
        if (str == null || open == null || close == null) {
            return null;
        }
        final int start = str.indexOf(open);
        if (start != -1) {
            final int end = str.indexOf(close, start + open.length());
            if (end != -1) {
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }

    public static String substringAfter(final String str, final String separator) {
        if (empty(str)) {
            return str;
        }
        if (separator == null) {
            return "";
        }
        final int pos = str.indexOf(separator);
        if (pos == -1) {
            return "";
        }
        return str.substring(pos + separator.length());
    }

    public static String getNumber(String dis, String code) {
        int countStart = 0;
        if (!empty(dis)) {
            countStart = dis.length();
        }
        int countEnd = 0;
        if (!empty(code)) {
            countEnd = code.length();
        }
        int totalCount = 12 - (countStart + countEnd);
        String str = "";
        for (int i = 0; i < totalCount; i++) {
            str += "*";
        }
        String resStr = dis + str + code + "";
        resStr = resStr.length() > 12 ? code : resStr;
        return resStr.length() > 12 ? resStr.substring(0, 12) : resStr;
    }

    public static String getTimeStr() {
        Calendar calendar = Calendar.getInstance();
        return (calendar.getTime().getTime() + "").substring(1, 13);
    }

    public static String getFullStr(Object obj) {
        String str = (String) obj;
        String[] pats = {"\\,", "\\、", "\\，"};
        if (StringUtil.notBlank(str)) {
            for (int i = 0; i < pats.length; i++) {
                str = str.replaceAll(pats[i], " ");
            }
        }
        return str;
    }

    public static String decodeStr(Object s, String code) {
        try {
            if (s != null && !StringUtil.empty((String) s)) {
                return java.net.URLDecoder.decode((String) s, code);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }
        return (String) s;
    }

    /**
     * unicode转为中文
     *
     * @param str
     * @return
     */
    public static String decodeUnicode(String str) {
        if (str == null) {
            return "";
        }
        Charset set = Charset.forName("UTF-16");
        Pattern p = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
        Matcher m = p.matcher(str);
        int start = 0;
        int start2 = 0;
        StringBuffer sb = new StringBuffer();
        while (m.find(start)) {
            start2 = m.start();
            if (start2 > start) {
                String seg = str.substring(start, start2);
                sb.append(seg);
            }
            String code = m.group(1);
            int i = Integer.valueOf(code, 16);
            byte[] bb = new byte[4];
            bb[0] = (byte) ((i >> 8) & 0xFF);
            bb[1] = (byte) (i & 0xFF);
            ByteBuffer b = ByteBuffer.wrap(bb);
            sb.append(String.valueOf(set.decode(b)).trim());
            start = m.end();
        }
        start2 = str.length();
        if (start2 > start) {
            String seg = str.substring(start, start2);
            sb.append(seg);
        }
        return sb.toString();
    }

    /**
     * unicode转为中文
     *
     * @param str1
     * @return
     */
    public static String decodeUnicode(StringBuffer str1) {
        String str = str1.toString();
        return decodeUnicode(str);

    }

    public static String getListString(List<String> list) {
        String str = "";
        for (String s : list) {
            str += s + ",";
        }
        return str.substring(0, str.length() > 1 ? str.length() - 1 : 0);
    }

    public static String fillPrefixZero(String str, int expectedLength) {
        int len = str.length();
        if (len < expectedLength) {
            return String.join("", Collections.nCopies(expectedLength - len, "0")) + str;
        }
        return str;
    }

    public static String removePrefixZero(String str) {
        int beginIdx = 0;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0') {
                beginIdx = i;
                break;
            }
        }
        return str.substring(beginIdx);
    }

    public static String removeZero(String str) {

        if (str.indexOf(".") > -1 && (str.lastIndexOf("0") + 1) == str.length()) {
//            int index=str.indexOf(".");
            return removeZero(str.substring(0, str.length() - 1));
        } else if (str.indexOf(".") > -1 && (str.lastIndexOf(".") + 1) == str.length()) {
            return removeZero(str.substring(0, str.length() - 1));
        } else {
            return str;
        }
    }

    /**
     * 移除换行
     *
     * @param str
     * @returns {void|XML|string|*|{by}|Node}
     */
    public static String replaceBlank(String str) {
        String str2 = "";
        if (StringUtil.notEmpty(str)) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            str2 = m.replaceAll("");
        }
        return str2;
    }

    /**
     * 匹配是否为数字
     *
     * @param str 可能为中文，也可能是-19162431.1254，不使用BigDecimal的话，变成-1.91624311254E7
     * @return
     * @author yutao
     * @date 2016年11月14日下午7:41:22
     */
    public static boolean isNumeric(String str) {
        if (StringUtil.empty(str)) {
            return false;
        } else if (StringUtil.empty(str.trim())) {
            return false;
        }
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }

        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static String numberToIntWithSeparator(Double number) {
        if (number == null)
            return "--";
        DecimalFormat df = new DecimalFormat("###,##0");
        return df.format(number);
    }

    public static String doubleToStringWithSeparator(Double number) {
        if (number == null)
            return "--";
        DecimalFormat df = new DecimalFormat("###,##0.00");
        return df.format(number);
    }

    //判断是否含有中文
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    // 根据Unicode编码完美的判断中文汉字和符号
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    public static Double getNumberForPer(String valStr) {
        if (valStr.contains("%")) {
            return Double.parseDouble(valStr.split("%")[0] + "") / 100;
        } else {
            return Double.parseDouble(valStr);
        }
    }


    /**
     * 获取首字符
     *
     * @param name
     * @return
     */
    public static String getFirstLetters(String name) {
        if (StringUtil.notEmpty(name)) {
            String[] nameList = name.split(" ");
            String firstLetters = "";
            for (String letter : nameList) {
                if (StringUtil.notEmpty(letter) && letter.length() > 0) {
                    firstLetters += letter.substring(0, 1).toUpperCase();
                }
            }
            return firstLetters;
        } else {
            return name;
        }
    }

    public static String getMarket(String code) {
        String market = "None";
        if (code.contains(".SH") || code.contains(".SZ")) {
            market = "CN";
        } else if (code.contains(".HK")) {
            market = "HK";
        } else if (code.contains(".O") || code.contains(".A") || code.contains(".N") || code.contains(".P")) {
            market = "US";
        } else
            market = "US";//其他市场在下订单到Paladyne的时候当做US
        return market;
    }

//    /**
//     * 将字符串中的中文转化为拼音,其他字符不变
//     *
//     * @param inputString
//     * @return
//     */
//    public static String getPingYin(String inputString) {
//        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
//        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//        format.setVCharType(HanyuPinyinVCharType.WITH_V);
//
//        char[] input = inputString.trim().toCharArray();
//        String output = "";
//
//        try {
//            for (int i = 0; i < input.length; i++) {
//                if (Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
//                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
//                    output += temp[0];
//                } else
//                    output += Character.toString(input[i]);
//            }
//        } catch (BadHanyuPinyinOutputFormatCombination e) {
//            e.printStackTrace();
//        }
//        return output.toUpperCase();
//    }
//    /**
//     * 将字符串中的中文转化为拼音,其他字符不变
//     *
//     * @param inputString
//     * @Parem split
//     * @return
//     */
//    public static String getNamePingYin(String inputString, String split) {
//        if(StringUtil.empty(inputString)){
//            return inputString;
//        }
//        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
//        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//        format.setVCharType(HanyuPinyinVCharType.WITH_V);
//
//        char[] input = inputString.trim().toCharArray();
//        String output = "";
//
//        try {
//            for (int i = 0; i < input.length; i++) {
//                if (Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
//                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
//                    output += firstUpperCase(temp[0]);
//
//                } else {
//                    output += input[i] ;
//                }
//                if(i==0){
//                    output+=split;
//                }
//            }
//        } catch (BadHanyuPinyinOutputFormatCombination e) {
//            e.printStackTrace();
//        }
//        return output;
//    }
//    /**
//     * 将字符串中的中文转化为拼音,其他字符不变
//     *
//     * @param inputString
//     * @return
//     */
//    public static String getPingYin(String inputString,String split) {
//        if(StringUtil.empty(inputString)){
//            return inputString;
//        }
//        String _split="";
//        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
//        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//        format.setVCharType(HanyuPinyinVCharType.WITH_V);
//
//        char[] input = inputString.trim().toCharArray();
//        String output = "";
//
//        try {
//            for (int i = 0; i < input.length; i++) {
//                if (Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
//                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
//                    output += _split+firstUpperCase(temp[0]);
//
//                } else {
//                    output += _split+ input[i] ;
//                }
//              _split=split;
//             }
//        } catch (BadHanyuPinyinOutputFormatCombination e) {
//            e.printStackTrace();
//        }
//        return output;
//    }

    public static String firstUpperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 脱敏 显示前4 后4
     *
     * @param idCardNum
     * @return
     */
    public static String cardString(String idCardNum) {
        if (StringUtil.notEmpty(idCardNum) && idCardNum.length() > 8) {
            return idCardNum.substring(0, 4) + "****" + idCardNum.substring(idCardNum.length() - 4);
        }
        return idCardNum;
    }

    /**
     * 脱敏 显示前1 后1
     *
     * @param name
     * @return
     */
    public static String nameString(String name) {
        if (StringUtil.notEmpty(name)) {
            return name.substring(0, 1) + "*" + (name.length() > 2 ? name.substring(name.length() - 1) : "");
        }
        return name;
    }

}
