package com.taskManager.service.converter;

import java.time.LocalDate;
import java.util.Date;

public interface DateConverter {
    Date convertLocalToDate(LocalDate localDate);
    LocalDate convertDateToLocal(Date date);
    Date convertToDate(String strDate);

//    Date convertToDeadLineDate(Date date);

}
