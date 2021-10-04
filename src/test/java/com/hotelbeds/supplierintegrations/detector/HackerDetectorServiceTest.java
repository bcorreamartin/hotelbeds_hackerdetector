package com.hotelbeds.supplierintegrations.detector;

import com.hotelbeds.supplierintegrations.hackertest.detector.service.converter.LogRowConverterImpl;
import com.hotelbeds.supplierintegrations.hackertest.detector.service.counter.CounterImpl;
import com.hotelbeds.supplierintegrations.hackertest.detector.HackerDetector;
import com.hotelbeds.supplierintegrations.hackertest.detector.HackerDetectorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HackerDetectorServiceTest {

    HackerDetector hackerDetector;

    @BeforeEach
    void beforeEach() {
        hackerDetector = new HackerDetectorImpl(new LogRowConverterImpl(), new CounterImpl());
    }

    @Test
    void succesAttempt_returnNull() {
        //WHEN
        String result1 = hackerDetector.parseLine("80.238.9.179,1336129471,SIGNIN_SUCCESS,Will.Smith");

        //THEN
        assertNull(result1);

    }

    @Test
    void fiveFailedSameIpMoreThanFiveMinutes_returnNull() {

        //WHEN
        String result1 = hackerDetector.parseLine("80.238.9.179,1336129471,SIGNIN_FAILURE,Will.Smith");
        String result2 = hackerDetector.parseLine("80.238.9.179,1336129500,SIGNIN_FAILURE,Will.Smith");
        String result3 = hackerDetector.parseLine("80.238.9.179,1336129540,SIGNIN_FAILURE,Will.Smith");
        String result4 = hackerDetector.parseLine("80.238.9.179,1336129580,SIGNIN_FAILURE,Will.Smith");
        String result5 = hackerDetector.parseLine("80.238.9.179,1336129900,SIGNIN_FAILURE,Will.Smith");

        //THEN
        assertNull(result1);
        assertNull(result2);
        assertNull(result3);
        assertNull(result4);
        assertNull(result5);
    }

    @Test
    void fiveFailedSameIpManyThanFiveMinutes_returnIp() {

        //WHEN
        String result1 = hackerDetector.parseLine("80.238.9.179,1336129471,SIGNIN_FAILURE,Will.Smith");
        String result2 = hackerDetector.parseLine("80.238.9.179,1336129500,SIGNIN_FAILURE,Will.Smith");
        String result3 = hackerDetector.parseLine("80.238.9.179,1336129540,SIGNIN_FAILURE,Will.Smith");
        String result4 = hackerDetector.parseLine("80.238.9.179,1336129580,SIGNIN_FAILURE,Will.Smith");
        String result5 = hackerDetector.parseLine("80.238.9.179,1336129600,SIGNIN_FAILURE,Will.Smith");

        //THEN
        assertNull(result1);
        assertNull(result2);
        assertNull(result3);
        assertNull(result4);
        assertEquals("80.238.9.179", result5);
    }
}
