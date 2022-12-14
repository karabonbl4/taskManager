package com.taskManager.service.converter.impl;

import com.taskManager.service.converter.DateConverter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@Component
public class LocalDateConverter implements DateConverter {

    @Override
    public Date convertLocalToDate(LocalDate localDate){
        return Date.from(Objects.requireNonNullElseGet(localDate, LocalDate::now).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public LocalDate convertDateToLocal(Date date) {
        return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    @Override
    public Date convertToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.ENGLISH);
        Date date;
        try {
            date = formatter.parse(strDate);
        } catch (ParseException e) {
            System.err.print(e);
            date = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        }
        return date;
    }
}
