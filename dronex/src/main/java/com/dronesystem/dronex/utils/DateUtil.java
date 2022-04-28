package com.dronesystem.dronex.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;



@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {
    
    public static YearMonth getCardExpirationFullDate(String cardExpirationData) {
        return YearMonth.parse(cardExpirationData, DateTimeFormatter.ofPattern("MM/uu"));
    }
    public static String getYearFromDate(YearMonth date) {
        return date.format(DateTimeFormatter.ofPattern("uu"));
    }

    public static String getMonthFromDate(YearMonth date) {
        return date.format(DateTimeFormatter.ofPattern("MM"));
    }

    public static Instant startOfDayToInstant(LocalDate date) {
        return date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
    }

    public static Instant endOfDayToInstant(LocalDate date) {
        return startOfDayToInstant(date).plus(Duration.ofDays(1));
    }

    public static LocalDate startOfCurrentMonth() {
        LocalDate now = LocalDate.now();
        return LocalDate.of(now.getYear(), now.getMonth(), 1);
    }

    public static LocalDate endOfCurrentMonth() {
        return startOfCurrentMonth().plusMonths(1);
    }

    public static Date dateFromString(String date, String format) throws ParseException {
        SimpleDateFormat formatter=new SimpleDateFormat(format);
        return formatter.parse(date);
    }
    public static LocalDate toLocalDateFormat(Date startDate) {
        LocalDate localDateFormat = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDateFormat;
    }

    public static Date fromLocalDateFormat(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime toLocalDateTimeFormat(Date startDate) {
        LocalDateTime localDateTimeFormat = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTimeFormat;
    }

    public static Date fromLocalDateTimeFormat(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date convertToDate(String dateString) {

        SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat formatter2 = new SimpleDateFormat("MM:dd:yyyy");
        SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {

            if (dateString.indexOf("-") > 0) {
                date = formatter3.parse(dateString);
            } else if (dateString.indexOf(":") > 0) {
                date = formatter2.parse(dateString);
            } else {
                date = formatter1.parse(dateString);
            }

            return date;

        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("Date format error:" + e.getMessage());
        }

    }

    public static String convertToText(Date date) {

        if (date == null) {
            return "";
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ZoneId zoneId = ZoneId.of("Africa/Lagos");
        dateFormat.setTimeZone(TimeZone.getTimeZone(zoneId));

        return dateFormat.format(date);
    }

    public static String toYearMonth(Date dateString) {
        DateFormat formatter = new SimpleDateFormat("MMM yyyy");
        return formatter.format(dateString);
    }

    public static Date getDateInstance() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // Set time fields to zero
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        // Put it back in the Date object
        date = cal.getTime();
        return date;
    }
}
