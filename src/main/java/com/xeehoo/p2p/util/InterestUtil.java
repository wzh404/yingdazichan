package com.xeehoo.p2p.util;

import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by wangzunhui on 2016/1/8.
 */
public class InterestUtil {
    /**
     * 按月还款周期
     *
     * @param investDay
     * @return
     */
    public static Integer getRepayCycle(String investDay){
        int length = investDay.length();

        String due = investDay.substring(0, length - 1);
        String unit = investDay.substring(length - 1, length);
        if ("M".equalsIgnoreCase(unit)){
            return Integer.parseInt(due);
        }
        else if ("Y".equalsIgnoreCase(unit)){
            return Integer.parseInt(due) * 12;
        }
        else{
            return 1;
        }
    }

    /**
     *
     * @return
     */
    public static Date tomorrow(){
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(new Date());
        gc.add(GregorianCalendar.DATE, 1);

        return gc.getTime();
    }

    /**
     * 计算结息日期
     *
     * @param date
     * @param investDay
     * @return
     */
    public static Date calculateInvestClosingDate(Date date, String investDay){
        int length = investDay.length();

        String due = investDay.substring(0, length - 1);
        String unit = investDay.substring(length - 1, length);

        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);

        int step = 0;
        if ("Y".equalsIgnoreCase(unit)){
            step = Integer.parseInt(due);
            gc.add(GregorianCalendar.YEAR, step);
        }
        if ("M".equalsIgnoreCase(unit)){
            step = Integer.parseInt(due);
            gc.add(GregorianCalendar.MONTH, step);
        }
        if ("W".equalsIgnoreCase(unit)){
            step = Integer.parseInt(due);
            gc.add(GregorianCalendar.WEEK_OF_YEAR, step);
        }
        if ("D".equalsIgnoreCase(unit)){
            step = Integer.parseInt(due);
            gc.add(GregorianCalendar.DATE, step);
        }

        return gc.getTime();
    }

    /**
     * 转换为JAVA8日期
     *
     * @param d
     * @return
     */
    public static LocalDate toLocalDate(Date d){
        Instant instant = d.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        return zdt.toLocalDate();
    }

    /**
     * java8日期转换为Date
     *
     * @param localDateTime
     * @return
     */
    public static Date toDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获得两个日期的间隔天数
     *
     * @param s
     * @param e
     * @return
     */
    public static long calculateIntervals(Date s, Date e){
        return ChronoUnit.DAYS.between(InterestUtil.toLocalDate(s), InterestUtil.toLocalDate(e));
    }

    /**
     * 利息计算 = 本金 * 年化率 * (天数 / 360)
     *
     * @param amount
     * @param rate
     * @param days
     * @return
     */
    public static BigDecimal calculateInterest(BigDecimal amount, BigDecimal rate, long days){
        return amount.multiply(rate).multiply(new BigDecimal(days / 360.0 / 100.0));
    }
}
