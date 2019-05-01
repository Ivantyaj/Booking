package com.booking.utils;

import org.joda.time.DateTimeComparator;

import java.util.Date;

public class DateUtils {

    public static boolean compareDates(Date firstDate, Date secondDate) {
        DateTimeComparator dateTimeComparator = DateTimeComparator.getDateOnlyInstance();
        System.err.println("Start date :" +firstDate);
        System.err.println("Leaving date :" +secondDate);
        return dateTimeComparator.compare(firstDate, secondDate) > 0;
    }
}
