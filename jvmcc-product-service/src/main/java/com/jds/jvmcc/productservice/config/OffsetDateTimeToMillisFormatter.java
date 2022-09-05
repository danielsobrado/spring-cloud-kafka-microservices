package com.jds.jvmcc.productservice.config;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Locale;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-14
 */
public class OffsetDateTimeToMillisFormatter implements Formatter<OffsetDateTime> {
    
    @Override
    public OffsetDateTime parse(String text, Locale locale) throws ParseException {
        long millis = Long.parseLong(text);
        return OffsetDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneOffset.UTC);
    }

    @Override
    public String print(OffsetDateTime object, Locale locale) {
        return "" + object.toInstant().toEpochMilli();
    }

}