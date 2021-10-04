package com.hotelbeds.supplierintegrations.hackertest.detector.service.converter;

import com.hotelbeds.supplierintegrations.hackertest.detector.model.LogRow;

public interface LogRowConverter {
    public LogRow parse(String row);
}
