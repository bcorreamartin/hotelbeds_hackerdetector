package com.hotelbeds.supplierintegrations.hackertest.detector.service.counter;

import com.hotelbeds.supplierintegrations.hackertest.detector.model.LogRow;

public interface Counter {
    boolean failedAttemptsCheck(LogRow logRow);

}
