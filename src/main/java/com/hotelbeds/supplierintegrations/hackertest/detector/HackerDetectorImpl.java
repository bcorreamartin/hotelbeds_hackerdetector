package com.hotelbeds.supplierintegrations.hackertest.detector;

import com.hotelbeds.supplierintegrations.hackertest.detector.service.converter.LogRowConverter;
import com.hotelbeds.supplierintegrations.hackertest.detector.service.converter.LogRowConverterImpl;
import com.hotelbeds.supplierintegrations.hackertest.detector.service.counter.Counter;
import com.hotelbeds.supplierintegrations.hackertest.detector.model.LogRow;
import com.hotelbeds.supplierintegrations.hackertest.detector.service.counter.CounterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HackerDetectorImpl implements HackerDetector {

    private LogRowConverter logEntryParser;

    private Counter counter;

    @Autowired
    public HackerDetectorImpl(LogRowConverterImpl logEntryParser, CounterImpl counter) {
        this.logEntryParser = logEntryParser;
        this.counter = counter;
    }

    public String parseLine(String line) {

        LogRow logEntry = logEntryParser.parse(line);

        if (counter.failedAttemptsCheck(logEntry)) {
            return logEntry.getIpAdress();
        } else {
            return null;
        }
    }
}
