package com.hotelbeds.supplierintegrations.detector.converter;

import com.hotelbeds.supplierintegrations.hackertest.detector.service.converter.LogRowConverter;
import com.hotelbeds.supplierintegrations.hackertest.detector.service.converter.LogRowConverterImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class LogRowConverterTest {

    @Autowired
    private LogRowConverter logEntryParser;

    @BeforeEach
    void beforeEach() {
        logEntryParser = new LogRowConverterImpl();
    }

    @Test
    void parseRow_returnRunTimeException() {
        assertThrows(RuntimeException.class, () -> logEntryParser.parse("80.238.9.179,1336129471,SIGNIN_FAILURE,Will.Smith,Exception"));

    }

    @Test
    void parseRow_returnNumberFormatException() {
        assertThrows(RuntimeException.class, () -> logEntryParser.parse("80.238.9.179,ERROR NUMBER,SIGNIN_SUCCESS,Will.Smith"));

    }
}