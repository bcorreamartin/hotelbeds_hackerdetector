package com.hotelbeds.supplierintegrations.hackertest.detector.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class LogRow {

    private final String ipAdress;
    private final LocalDateTime dateTime;
    private final AttempAction attempAction;
    private final String userName;
}
