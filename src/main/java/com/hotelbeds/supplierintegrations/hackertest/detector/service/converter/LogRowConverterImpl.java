package com.hotelbeds.supplierintegrations.hackertest.detector.service.converter;

import com.hotelbeds.supplierintegrations.hackertest.detector.model.AttempAction;
import com.hotelbeds.supplierintegrations.hackertest.detector.model.LogRow;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.StringTokenizer;

@Component
public class LogRowConverterImpl implements LogRowConverter{

    public LogRow parse(String row) {

        if (row != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(row, ",");
            if (stringTokenizer.countTokens() == 4) {
                return new LogRow(stringTokenizer.nextToken(), stringEpochToLocalDateTime(stringTokenizer.nextToken()), stringToAttempAction(stringTokenizer.nextToken()), stringTokenizer.nextToken());
            }
            throw new RuntimeException("LogRowConverter.parse() - stringTokenizer count failed: " + stringTokenizer.countTokens());
        }
        throw new RuntimeException("LogRowConverter.parse() - Row is null");
    }

    private LocalDateTime stringEpochToLocalDateTime(String epoch) {

        try {
            long epochInMillis = Long.valueOf(epoch) * 1000;
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochInMillis), ZoneId.systemDefault());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("LogRowConverter.stringEpochToLocalDateTime() - String epoch: " + epoch);
        }
    }

    private AttempAction stringToAttempAction(String action) {

        if (AttempAction.SIGNIN_SUCCESS.toString().equals(action)) {
            return AttempAction.SIGNIN_SUCCESS;
        } else if (AttempAction.SIGNIN_FAILURE.toString().equals(action)) {
            return AttempAction.SIGNIN_FAILURE;
        } else {
            throw new RuntimeException("LogRowConverter.stringToAttempAction() - Invalid action: " + action);
        }
    }
}