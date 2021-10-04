package com.hotelbeds.supplierintegrations.hackertest.detector.service.counter;

import com.hotelbeds.supplierintegrations.hackertest.detector.model.AttempAction;
import com.hotelbeds.supplierintegrations.hackertest.detector.model.FailedAttempts;
import com.hotelbeds.supplierintegrations.hackertest.detector.model.LogRow;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class CounterImpl implements Counter {

    private static Map<String, FailedAttempts> failedAttemptsList = Collections.synchronizedMap(new HashMap<>());
    private final int MINUTEWINDOW = 5;
    private final int MAXATTEMPT = 5;

    @Override
    public boolean failedAttemptsCheck(LogRow logRow) {

        if (AttempAction.SIGNIN_SUCCESS.equals(logRow.getAttempAction())) {
            return false;
        }

        return synchronizeCounter(logRow);
    }

    private synchronized boolean synchronizeCounter(LogRow logRow) {
        checkOldAndRemove(logRow);

        FailedAttempts failedLoginAttempts = getFailedAttemptsForLogRow(logRow);

        int failedAttemptsCount = failedLoginAttempts.getFailedAttemptsCountWithinTimeWindow(logRow.getDateTime(), MINUTEWINDOW);
        return failedAttemptsCount >= MAXATTEMPT;

    }

    private FailedAttempts getFailedAttemptsForLogRow(LogRow logRow) {

        if (failedAttemptsList.get(logRow.getIpAdress()) == null) {
            failedAttemptsList.put(logRow.getIpAdress(), new FailedAttempts(logRow.getDateTime()));
        }
        return failedAttemptsList.get(logRow.getIpAdress());
    }

    private void checkOldAndRemove(LogRow logRow) {
        for (Map.Entry entry : failedAttemptsList.entrySet()) {
            if (((FailedAttempts) entry.getValue()).firstAttemptIsOlderThan(logRow.getDateTime(), MINUTEWINDOW)) {
                failedAttemptsList.remove(entry.getKey());
            }
        }
    }

}
