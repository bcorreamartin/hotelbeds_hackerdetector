package com.hotelbeds.supplierintegrations.timecalculation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    Calculator calculator = new CalculatorImpl();

    @Test
    void sameTime_shouldReturnZero() {
        String timeStamp1 = "Thu, 30 Dec 2021 19:45:00 +0200";
        String timeStamp2 = "Thu, 30 Dec 2021 19:45:00 +0200";

        //WHEN
        long result = calculator.minutesBetweenTimestamps(timeStamp1, timeStamp2);

        //THEN
        assertEquals(0, result);
    }

    @Test
    void fiveMinutesDifference_shouldReturnFive() {
        String timeStamp1 = "Thu, 30 Dec 2021 19:45:00 +0200";
        String timeStamp2 = "Thu, 30 Dec 2021 19:40:00 +0200";

        //WHEN
        long result = calculator.minutesBetweenTimestamps(timeStamp1, timeStamp2);

        //THEN
        assertEquals(5, result);
    }

    @Test
    void fiveMinutesInvertedTimestampsDifference_shouldReturnFive() {
        String timeStamp1 = "Thu, 30 Dec 2021 19:40:00 +0200";
        String timeStamp2 = "Thu, 30 Dec 2021 19:45:00 +0200";

        //WHEN
        long result = calculator.minutesBetweenTimestamps(timeStamp1, timeStamp2);

        //THEN
        assertEquals(5, result);
    }

    @Test
    void differentTimeZone_shouldReturnOneHundredTwenty() {
        String timeStamp1 = "Thu, 30 Dec 2021 19:45:00 +0400";
        String timeStamp2 = "Thu, 30 Dec 2021 19:45:00 +0200";

        //WHEN
        long result = calculator.minutesBetweenTimestamps(timeStamp1, timeStamp2);

        //THEN
        assertEquals(120, result);
    }

    @Test
    void fiveMinutesdifferentTimeZone_shouldReturnOneHundredFifteen() {
        String timeStamp1 = "Thu, 30 Dec 2021 19:45:00 +0400";
        String timeStamp2 = "Thu, 30 Dec 2021 19:40:00 +0200";

        //WHEN
        long result = calculator.minutesBetweenTimestamps(timeStamp1, timeStamp2);

        //THEN
        assertEquals(115, result);
    }
}
