package com.hotelbeds.supplierintegrations.timecalculation;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class CalculatorImpl implements Calculator {

    final DateTimeFormatter df = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("EEE, dd MMM yyyy HH:mm:ss Z")
            .toFormatter(Locale.ENGLISH);

    @Override
    public long minutesBetweenTimestamps(String time1, String time2) {

        ZonedDateTime zonedDateTime1 = ZonedDateTime.parse(time1, df);
        ZonedDateTime zonedDateTime2 = ZonedDateTime.parse(time2, df);

        return Math.abs(Duration.between(zonedDateTime1, zonedDateTime2).toMinutes());
    }
}