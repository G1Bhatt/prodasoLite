package com.example.demo.util;

import lombok.extern.log4j.Log4j2;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;

@Log4j2
public class DateTimeUtil {

    public static Long epochTimeNow() {
        return LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}
