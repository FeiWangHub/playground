package com.cloudkitchens.feisolution.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final SimpleDateFormat HHmmssSSS = new SimpleDateFormat("HH:mm:ss:SSS");

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

}
