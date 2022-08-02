package com.fei.playground.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    public static final SimpleDateFormat HHmmssSSS = new SimpleDateFormat("HH:mm:ss:SSS");

    public static DateFormat formatFullTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss mmm");
    public static DateFormat formatFullTime1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static DateFormat formatFullTime2 = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    public static DateFormat formatFullTime3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static DateFormat formatNowDate = new SimpleDateFormat("yyyyMMdd");
    public static DateFormat formatNowDate2 = new SimpleDateFormat("yyyy-MM-dd");
    public static DateFormat formatNowDate3 = new SimpleDateFormat("dd-MM-yyyy");
    public static DateFormat formatNowDate4 = new SimpleDateFormat("yyyy/MM/dd");
    public static DateFormat formatNowDate5 = new SimpleDateFormat("MM/dd/yyyy");
    public static DateFormat formatNowTime = new SimpleDateFormat("HH:mm:ss");

    /**
     * add specified seconds to target date
     * @param time target Date
     * @param second int
     * @return result Date
     */
    public static Date addSecond(Date time, int second){
        Calendar cl = Calendar.getInstance();
        cl.setTime(time);
        cl.add(Calendar.SECOND, second);
        return cl.getTime();
    }

    public static String format(Date date, String format) {
        DateFormat format1 = new SimpleDateFormat(format);
        return format1.format(date);
    }

    public static String sysGlobalFormat(Date date) {
        return formatNowDate2.format(date);
    }

    public static String formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
        }
        if (hour > 0) {
            sb.append(hour + "小时");
        }
        if (minute > 0) {
            sb.append(minute + "分");
        }
        if (second > 0) {
            sb.append(second + "秒");
        }
        if (milliSecond > 0) {
            sb.append(milliSecond + "毫秒");
        }
        return sb.toString();
    }

    public static Timestamp getNow() { return new Timestamp(System.currentTimeMillis());}

    public static String getNowDate() {
        return formatNowDate.format(new Date());
    }
    public static String getNowDate4() {
        return formatNowDate4.format(new Date());
    }
    public static String getNowDate2() {
        return formatNowDate2.format(new Date());
    }

    public static String getNowTime() {
        return formatNowTime.format(new Date());
    }

    public static String getNowFullTime_3() {
        return formatFullTime3.format(new Date());
    }

    public static String getNowFullTime_2() {
        return formatFullTime2.format(new Date());
    }

    public static String getNowFullTime_1() {
        return formatFullTime1.format(new Date());
    }

    public static String getNowFullTime() {
        return formatFullTime.format(new Date());
    }

    public static String getYearAgoTime(int number) {
        Date date = new Date();
        date.setYear(date.getYear() + number);
        return formatNowDate2.format(date);
    }

    public static String getDayAgoTime(int number) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, number);
        return new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
    }
    public static String nextDay(String date){
        try {
            Calendar cale = Calendar.getInstance();
            cale.setTime(string2Date(date));
            cale.add(Calendar.DATE,1);
            return sysGlobalFormat(cale.getTime());
        }catch(Exception e){
            return null;
        }
    }

    /**
     * 获得n天前或者后的日期string
     * @param dateStr yyyy-mm-dd
     * @param offset for yesterday, use -1
     * @return yyyy-mm-dd
     * @throws ParseException ParseException
     */
    public static String getNDayAgoOrLater(String dateStr, int offset) throws ParseException {
        Calendar cal = Calendar.getInstance();
        Date d = DateUtil.string2Date(dateStr);
        cal.setTime(d);
        cal.add(Calendar.DATE, offset);
        return DateUtil.dateToString2(cal.getTime());
    }
    /**
     * 获得n天前或者后的日期string
     * @param dateStr yyyy-mm-dd
     * @param offset for yesterday, use -1
     * @return yyyy-mm-dd
     * @throws ParseException ParseException
     */
    public static Timestamp getTimestampByOffset(Object dateStr, int offset)  {
        try {
            Calendar cal = Calendar.getInstance();
            Date d = DateUtil.str2TimeStamp(getStrByDate(dateStr));
            cal.setTime(d);
            cal.add(Calendar.DATE, offset);
            return  new Timestamp(cal.getTime().getTime())   ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * date 转str
     * @param date
     * @return
     */
    public static String getStrByDate(Object date){
        if (date instanceof java.lang.String) {
            return DateUtil.timestamp2Str(DateUtil.str2TimeStamp((String) date)).replaceAll("-","");
        } else if (date instanceof java.sql.Timestamp) {
            return DateUtil.timestamp2Str((Timestamp) date).replaceAll("-","");
        }  else if (date instanceof java.util.Date) {
            return DateUtil.dateToString2((Date) date).replaceAll("-","");
        } else {
            throw new UnsupportedOperationException();
        }
    }
    /**
     * 获得n天前或者后的日期
     * @param date 日期
     * @param offset for yesterday, use -1
     * @return yyyy-mm-dd
     */
    public static Date getNDayAgoOrLater(Date date, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, offset);
        return cal.getTime();
    }

    /**
     * 判断日期是否在范围内
     */
    public static boolean isTimeInBetween(Timestamp date, Timestamp start, Timestamp end) {
        if (date.after(start) && date.before(end)) {
            return true;
        } else if (date.getTime() == start.getTime() || date.getTime() == end.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    public static String getMonthAgoTime(int month) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, month);
        return new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
    }

    public static String getMonthAgoTime(Timestamp date,int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);
        return new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
    }

    public static boolean isTimeInBetween(Date date, Date start, Date end) {
        Timestamp dateStamp = new Timestamp(date.getTime());
        Timestamp startStamp = new Timestamp(start.getTime());
        Timestamp endStamp = new Timestamp(end.getTime());
        return isTimeInBetween(dateStamp, startStamp, endStamp);
    }

    public static Timestamp getLastSecondOfDay(Timestamp date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static String getNMonthAgoOrLater(int month) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, month);
        return new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
    }

    public static String getNMonthAgoOrLater(Timestamp date, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);
        return new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
    }

    public static Date getNMonthAgoOrLater(Date date, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);
        return cal.getTime();
    }

    //年初
    public static String getYearFirstDayTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取前年的第一天
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.YEAR, 0);
        cale.set(Calendar.DAY_OF_YEAR, 1);
        return format.format(cale.getTime());
    }

    //年初
    public static Timestamp getYearFirstDayTime(Timestamp date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取前年的第一天
        Calendar cale = Calendar.getInstance();
        cale.setTime(new java.util.Date(date.getTime()));
        cale.add(Calendar.YEAR, 0);
        cale.set(Calendar.DAY_OF_YEAR, 1);
        return str2TimeStamp(format.format(cale.getTime()));
    }
    //年初
    public static Timestamp getYearLastDayTime(Timestamp date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取前年的第一天
        Calendar cale = Calendar.getInstance();
        cale.setTime(new java.util.Date(date.getTime()));
        cale.add(Calendar.YEAR, 1);
        cale.set(Calendar.DAY_OF_YEAR, -1);
        return str2TimeStamp(format.format(cale.getTime()));
    }
    //上年末
    public static Timestamp getLastYearLastDayTime(Timestamp date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取前年的第一天
        Calendar cale = Calendar.getInstance();
        cale.setTime(new java.util.Date(date.getTime()));
        cale.add(Calendar.YEAR, 0);
        cale.set(Calendar.DAY_OF_YEAR, 0);
        return str2TimeStamp(format.format(cale.getTime()));
    }

    //月初
    public static String getMonFirstDayTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取前月的第一天
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(cale.getTime());
    }

    //上月末
    public static String getLastMonLastDayTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取上个月的最后一天
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.MONTH, cale.get(Calendar.MONTH) - 1);
        cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
        return format.format(cale.getTime());
    }

    /**
     * 得到当前日期几个月之前的日期
     * @param month
     * @return
     */
    public static Timestamp getPreMonAgoTime(int month){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -month);
        return   str2TimeStamp(format.format(c.getTime()));
    }

    /**
     * 获取相对时间的月末
     *
     * @return
     */
    public static Timestamp getMonthLastDay(Timestamp time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取本月的最后一天
        Calendar cale = Calendar.getInstance();
        cale.setTime(new java.util.Date(time.getTime()));
        cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
        return str2TimeStamp(format.format(cale.getTime()));
    }

    /**
     * 获取相对时间的月初
     *
     * @return
     */
    public static Timestamp getMonthFirstDay(Timestamp time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取本月的 月初
        Calendar cale = Calendar.getInstance();
        cale.setTime(new java.util.Date(time.getTime()));
        cale.set(Calendar.DAY_OF_MONTH, cale.getActualMinimum(Calendar.DAY_OF_MONTH));
        return str2TimeStamp(format.format(cale.getTime()));
    }

    /**
     * 获取相对时间的上个月末
     *
     * @return
     */
    public static Timestamp getLastMonLastDayTimeDistanceDay(Timestamp time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取上个月的最后一天
        Calendar cale = Calendar.getInstance();
        cale.setTime(new java.util.Date(time.getTime()));
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return str2TimeStamp(format.format(cale.getTime()));
    }

    /**
     * 获取相对时间的上个月末
     *
     * @return
     */
    public static Timestamp getLastMonLastDayTimeDistanceDay(String time1) {
        return getLastMonLastDayTimeDistanceDay(str2TimeStamp(time1));
    }

    /**
     * 获取当前日期近一年的日期
     * @return
     */
    public static Timestamp getPrvYearDistanceDay(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        return str2TimeStamp(format.format(c.getTime()));
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static String getCurrYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return formatNowDate2.format(currYearFirst);
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static String getCurrYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return formatNowDate2.format(currYearLast);
    }

    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取几年前
     *
     * @return
     * @throws
     */
    public static int getYear(int number) {
        Calendar a = Calendar.getInstance();
        return a.get(Calendar.YEAR) + number;
    }

    public static Date getTodayWithoutTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date getDate(String str, String formatStr, String type) throws ParseException {
        if (StringUtil.notEmpty(type) && type.equals("English")) {
            SimpleDateFormat formatNowDate = new SimpleDateFormat(formatStr, Locale.ENGLISH);
            return formatNowDate.parse(str);
        } else {
            SimpleDateFormat formatNowDate = new SimpleDateFormat(formatStr);
            return formatNowDate.parse(str);
        }
    }

    /**
     * 实时行情校验规则 。股市开盘日期
     *
     * @return
     */
    public static boolean dateRule() {
        //获取当前时间进行时间规则校验
        Calendar now = Calendar.getInstance();
        int date = now.get(Calendar.HOUR_OF_DAY);//小时
        //int minu=now.get(Calendar.MINUTE);//分钟
        //国内时间  周一到周五  国内开盘时间 9:30到11:30以及13:00到15:00，
        //国内时间  周一到周五  香港开盘时间 10：00开盘，中午12：30前市休盘；下午2：30才开盘，至下午4：00才收市。
        //国内时间  周一到周六  美国开盘时间 晚上9点半到凌晨4点半
        //折合 为 区间 星期一                    09.00 -16.00  21.30-24.00
        //星期二到星期五（24时制）  00.00-04.30  09.30-12.30  13.00-16.00  21.30-24.00
        //               星期六    00.00-04.30
        //获取星期
        int weekDay = now.get(Calendar.DAY_OF_WEEK);
        if (Calendar.SUNDAY != weekDay) {
            if (Calendar.MONDAY == weekDay) {//星期一
                if ((date >= 9 && date < 16) || (date >= 21 && date < 24)) {
                    return true;
                }
            } else if (Calendar.SATURDAY == weekDay) { //星期六
                if (date >= 0 && date < 5) {
                    return true;
                }
            } else {//星期二到星期五
                if ((date >= 0 && date < 5) || (date >= 9 && date < 16) || (date >= 21 && date < 24)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 时间转str
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date string2DateGlobal(String dateStr) throws ParseException {
        String str = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        Date date = sdf.parse(dateStr);
        return date;
    }

    /**
     * 时间转str
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date string2Date2(String dateStr) throws ParseException {
        String str = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        Date date = sdf.parse(dateStr);
        return date;
    }

    /**
     * 时间转str
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date string2Date(String dateStr) throws ParseException {
        return formatNowDate2.parse(dateStr);
    }

    /**
     * 时间转str 加减天数
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static String string2Date2(String dateStr, int i) {
        String str = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        Calendar date = Calendar.getInstance();
        try {
            date.setTime(sdf.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date.set(Calendar.DATE, date.get(Calendar.DATE) + i);
        return sdf.format(date.getTime());
    }

    /**
     * Timestamp
     *
     * @param date
     * @return
     */
    public static Timestamp str2TimeStamp(String date) {
        Timestamp ts = null;
        if (StringUtil.empty(date)) {
            return null;
        }
        try {
            if (date.contains("-")) {
                if (date.split("-")[0].length() > 2) {
                    ts = new Timestamp(formatNowDate2.parse(date).getTime());
                } else {
                    ts = new Timestamp(formatNowDate3.parse(date).getTime());
                }

            } else if (date.contains("/")) {
                if (date.split("/")[0].length() > 2) {
                    ts = new Timestamp(formatNowDate4.parse(date).getTime());
                } else {
                    ts = new Timestamp(formatNowDate5.parse(date).getTime());
                }

            } else {
                ts = new Timestamp(formatNowDate.parse(date).getTime());
            }

        } catch (ParseException e) {
            e.printStackTrace();

        }
        return ts;
    }

    /**
     * timestamp转string
     * @param time
     * @param format 格式
     * @return
     */
    public static String timestamp2Str(Timestamp time,String format){
        DateFormat sdf = new SimpleDateFormat(format);
        String timeStr = sdf.format(time);
        return timeStr;
    }
    /**
     * timestamp转string
     * @param time
     * @return
     */
    public static String timestamp2Str(Timestamp time){
        return time!=null?new SimpleDateFormat("yyyy-MM-dd").format(time):"";
    }
    /**
     * 时间转str
     *
     * @param date
     * @return
     */
    public static String DateToString(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * @return String "yyyy-mm-dd"
     */
    public static String dateToString2(Date date) {
        return formatNowDate2.format(date);
    }

    /**
     * 获取两个日期之间的日期  不包前也不包后
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 日期集合
     */
    public static List<Timestamp> getBetweenTimestamps(Timestamp start, Timestamp end) {
        List<Timestamp> result = new ArrayList<>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        tempStart.add(Calendar.DAY_OF_YEAR, 1);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        while (tempStart.before(tempEnd)) {
            result.add(new Timestamp(tempStart.getTimeInMillis()));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }

    /**
     * 获取两个日期之间的日期 包前包后
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 日期集合
     */
    public static List<Timestamp> getBetweenTimestampsInclude(Timestamp start, Timestamp end) {
        List<Timestamp> result = new ArrayList<>();
        if (start.toString().substring(0,10) .equals(end.toString().substring(0,10)) ) {
            result.add(start);
            return result;
        } else {
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);
            tempStart.add(Calendar.DAY_OF_YEAR, 1);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            result.add(start);
            while (tempStart.before(tempEnd)) {
                result.add(new Timestamp(tempStart.getTimeInMillis()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
            result.add(end);
            return result;
        }
    }

    /**
     * 获取两个日期之间的日期 包前包后
     *
     * @param startStr 开始日期
     * @param endStr   结束日期
     * @return 日期集合
     */
    public static List<Timestamp> getBetweenTimestampsInclude(String startStr, String endStr) {
        if(StringUtil.empty(startStr)||StringUtil.empty(endStr)){
            return null;
        }else {
            startStr=startStr.substring(0,10)+" 00:00:00";
            endStr=endStr.substring(0,10)+" 00:00:00";
        }
        Timestamp start=Timestamp.valueOf(startStr);
        Timestamp end  =Timestamp.valueOf(endStr);
        List<Timestamp> result = new ArrayList<>();
        if (start.toString().substring(0,10) .equals(end.toString().substring(0,10)) ) {
            result.add(start);
            return result;
        } else {
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);
            tempStart.add(Calendar.DAY_OF_YEAR, 1);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            result.add(start);
            while (tempStart.before(tempEnd)) {
                result.add(new Timestamp(tempStart.getTimeInMillis()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
            result.add(end);
            return result;
        }
    }

    /**
     * 根据出身日期计算年龄
     * @param birth
     * @return
     */
    public static int getAgeByBirth(String birth){
        birth=birth.substring(0,10)+" 00:00:00";
        int age = 0;
        Calendar now = Calendar.getInstance();
        // 当前时间
        now.setTime(new Date());
        Calendar birthDay = Calendar.getInstance();
        birthDay.setTime(Timestamp.valueOf(birth));
        //如果传入的时间，在当前时间的后面，返回0岁
        if (birthDay.after(now)) {
            age = 0;
        } else {
            age = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
            if (now.get(Calendar.DAY_OF_YEAR) > birthDay.get(Calendar.DAY_OF_YEAR)) {
                age += 1;
            }
        }
        return age;
    }

    public static String getNowTradeDate(){
        Date today = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(today);
        if(now.get(Calendar.HOUR_OF_DAY)<=6){
            now.add(Calendar.DAY_OF_MONTH,-1);
        }
        return formatNowDate2.format(now.getTime());
    }



    public static int getYear(String date) {
        return  StringUtil.notEmpty(date)&&date.length()>=4? Integer.parseInt(date.substring(0,4)):0;
    }

    public static Timestamp str2FullTimeStamp(String date) {

        Timestamp ts = null;
        if (StringUtil.empty(date)) {
            return null;
        }
        try {
            if (date.contains("-")) {
                if (date.length() > 20) {
                    ts = new Timestamp(formatFullTime.parse(date).getTime());
                } else {
                    ts = new Timestamp(formatFullTime1.parse(date).getTime());
                }
            } else if (date.contains("/")) {
                ts = new Timestamp(formatFullTime3.parse(date).getTime());
            } else {
                ts = new Timestamp(formatFullTime2.parse(date).getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return ts;
    }
    /**
     　　 *字符串的日期格式的计算
     　　 */
    public static int daysBetween(String smdate,String bdate) throws ParseException{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }
    /**
     * 清除 时分秒
     * @param dividendPaymentDate
     * @return
     */
    public static Timestamp cleanHourMinSed(Timestamp dividendPaymentDate) {
        String dateStr=timestamp2Str(dividendPaymentDate,"yyyy-MM-dd")+" 00:00:00";
        return str2FullTimeStamp(dateStr);
    }

    public static int getDistanceDayFromNow(Timestamp dividendPaymentDate)   {
        try {
            return daysBetween( timestamp2Str(getNow(),"yyyy-MM-dd"),timestamp2Str(dividendPaymentDate,"yyyy-MM-dd") );
        }catch (Exception e){
            return 0;
        }
    }

    /**
     *
     * @param date
     * @return
     */
    public static boolean isNotWorkDay(Timestamp date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY;
    }

    /**
     * 获取monday of  this week
     * @return
     */
    public static Date getThisWeekMonday(Timestamp date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return    cal.getTime()  ;
    }

    public static String getThisWeekMondayString(Timestamp now) {
        return formatNowDate4.format(getThisWeekMonday(now));
    }

    /**
     * date 转timestamp
     * @param date
     * @return
     */
    public static Timestamp date2Timestamp(Date date) {
        return  new Timestamp(date.getTime());
    }

    public static Timestamp getTimestampByOffsetMonth(Object firstDay, int offset) {
        try {
            Calendar cal = Calendar.getInstance();
            Date d = DateUtil.str2TimeStamp(getStrByDate(firstDay));
            cal.setTime(d);
            cal.add(Calendar.MONTH, offset);
            return  new Timestamp(cal.getTime().getTime())   ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Timestamp getTimestampByOffsetYear(Object firstDay, int offset) {
        try {
            Calendar cal = Calendar.getInstance();
            Date d = DateUtil.str2TimeStamp(getStrByDate(firstDay));
            cal.setTime(d);
            cal.add(Calendar.YEAR, offset);
            return  new Timestamp(cal.getTime().getTime())   ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isMonDay(Timestamp startTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY ;

    }
    public static boolean isSunDay(Timestamp endTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(endTime);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ;

    }

    public static boolean isLastDayOfYear(Timestamp endTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(endTime);
        cal.add(Calendar.DAY_OF_YEAR,1);
        return cal.get(Calendar.DAY_OF_YEAR) == 1 ;
    }

    public static boolean isFirstDayOfYear(Timestamp startTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        return cal.get(Calendar.DAY_OF_YEAR) == 1 ;
    }

    public static boolean isLastDayOfMonth(Timestamp endTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(endTime);
        cal.add(Calendar.DAY_OF_MONTH,1);
        return cal.get(Calendar.DAY_OF_MONTH) == 1 ;
    }

    public static boolean isFirstDayOfMonth(Timestamp startTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        return cal.get(Calendar.DAY_OF_MONTH) == 1 ;
    }

    /**
     *获取本月最后一个工作日
     * @param date
     * @return
     */
    public static Date getLastDateOfMonth(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date parse = null;
        try {
            parse = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(parse);
        cal.add(Calendar.MONTH,1);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            cal.add(Calendar.DATE, -1);

        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            cal.add(Calendar.DATE, -2);

        }
        return cal.getTime();
    }
    /**
     *  成本日和计算日的规则
     *
     * @param initDate 成本日
     * @param calDate 计算日
     * @return
     */
    public static Double managementDayMonthRate(Timestamp initDate,  Timestamp  calDate) {
        //月初时间
        Timestamp monthFirstDay=getMonthFirstDay(calDate);
        //月末时间
        Timestamp monthLastDay=getMonthLastDay(calDate);
        //本月最后一个工作日
        Date lastDateOfMonth=    getLastDateOfMonth(DateUtil.timestamp2Str(calDate));
        //计算日月初的天数差
        int calDistance=getBetweenTimestampsInclude(monthFirstDay,calDate).size();
        //本月的天数
        int dayNumOfMonth=getBetweenTimestampsInclude(monthFirstDay,monthLastDay).size();
        //成本日早于月初
        if(initDate==null||initDate.before(monthFirstDay)){
            //计算日刚好是月末
            if(lastDateOfMonth.compareTo(calDate)==0 ){
                return 1d;
            }else {
                //计算日早于月末
                return   calDistance/(dayNumOfMonth*1.0d);
            }

        }else {
            //成本日和月初天数差
            int initDistance=getBetweenTimestampsInclude(monthFirstDay,initDate).size();
            //成本日晚于或等于月初 当天不收
            return   (calDistance-initDistance)/(dayNumOfMonth*1.0d);
        }

    }
    /**
     *
     * @param
     * @return
     */
    public static List<String> getStringList(List<Timestamp> timestampList) {
        List<String> stringList=new ArrayList<>();
        if(timestampList!=null && timestampList.size()>0){
            for(Timestamp timestamp:timestampList){
                stringList.add(timestamp2Str(timestamp));
            }
        }
        return  stringList;
    }
    /**
     * 获取两个日期之间的日期 包前包后 但是排除周末
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 日期集合
     */
    public static List<Timestamp> getBetweenTimestampsIncludeButWeekend(Timestamp start, Timestamp end) {
        List<Timestamp> result = new ArrayList<>();
        if (start.toString().substring(0,10) .equals(end.toString().substring(0,10)) ) {
            result.add(start);
            return result;
        } else {
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);
            tempStart.add(Calendar.DAY_OF_YEAR, 1);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            result.add(start);
            while (tempStart.before(tempEnd)) {
                Timestamp cacheTime=new Timestamp(tempStart.getTimeInMillis());
                if(!DateUtil.isNotWorkDay(cacheTime)){
                    result.add(cacheTime);
                }
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
            result.add(end);
            return result;
        }
    }
}
