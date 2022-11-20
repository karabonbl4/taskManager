package com.taskManager.service.mapper;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

public interface DateConverter {
    Date convertLocalToDate(LocalDate localDate);
    LocalDate convertDateToLocal(Date date);
    Date convertToDate(String strDate);
}
