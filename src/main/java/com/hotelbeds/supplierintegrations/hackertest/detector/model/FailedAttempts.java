package com.hotelbeds.supplierintegrations.hackertest.detector.model;

import java.time.LocalDateTime;

public class FailedAttempts {

    private int attempts = 1;

    private LocalDateTime firstAttempt;

    public FailedAttempts(LocalDateTime firstAttempt) {
        this.firstAttempt = firstAttempt;
    }


    public synchronized int getFailedAttemptsCountWithinTimeWindow(LocalDateTime lastAttempt, int timeWindowInMinutes) {

        if (firstAttempt.isBefore(lastAttempt) && firstAttempt.plusMinutes(timeWindowInMinutes).isAfter(lastAttempt)) {
            attempts++;
        } else {
            attempts = 1;
            firstAttempt = lastAttempt;
        }

        return attempts;
    }

    public synchronized boolean firstAttemptIsOlderThan(LocalDateTime localDateTime, int timeWindow) {
        return firstAttempt.plusMinutes(timeWindow).isBefore(localDateTime);
    }
}