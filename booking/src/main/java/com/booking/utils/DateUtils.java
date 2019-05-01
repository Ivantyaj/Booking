package com.booking.utils;

import org.joda.time.DateTimeComparator;

import java.util.Date;

public class DateUtils {

    public static boolean compareDates(Date firstDate, Date secondDate) {
        DateTimeComparator dateTimeComparator = DateTimeComparator.getDateOnlyInstance();
        return dateTimeComparator.compare(firstDate, secondDate) > 0;
    }
}
