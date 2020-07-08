package com.example.demo.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    public static Date now() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 문자열을 날짜로 반환한다.
     * @param strYMD yyyyMMdd 포맷의 문자열
     * @param setEndOfDay true:24시로 세팅, false:0시로 세팅
     * @return date
     * */
    public static Date toDate_yyyymmdd(String strYMD, boolean setEndOfDay) {
        int yyyymmdd = 0;
        try{
            yyyymmdd = Integer.parseInt(strYMD);
        }catch(Exception e){
            return null;
        }
        return getCalendar(yyyymmdd, setEndOfDay).getTime();
    }

    public static Calendar getCalendar(int yyyymmdd, boolean setEndOfDay) {
        Calendar calendar = new GregorianCalendar();
        if(setEndOfDay) {
            calendar.set((yyyymmdd / 10000), ((yyyymmdd / 100) % 100 - 1), yyyymmdd % 100, 23, 59, 59); //last time
        } else {
            calendar.set((yyyymmdd / 10000), ((yyyymmdd / 100) % 100 - 1), yyyymmdd % 100, 0, 0, 0); //start time
        }
        return calendar;
    }
}
