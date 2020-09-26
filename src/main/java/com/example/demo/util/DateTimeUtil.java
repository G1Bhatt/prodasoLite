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

    public static Time convertLocalTimeStringToSqlTime(String localTime) {
        try {
            return Time.valueOf(LocalTime.parse(localTime));
        } catch (DateTimeParseException ex) {
            log.error(
                    "DateTimeUtil:convertLocalTimeStringToSqlTime -  DateTimeParseException occurred while parsing String to LocalTime ",
                    ex);
            return null;
        } catch (Exception ex) {
            log.error(
                    "DateTimeUtil:convertLocalTimeStringToSqlTime -  Exception occurred while parsing String to LocalTime ",
                    ex.getMessage());
            return null;
        }

    }

}
