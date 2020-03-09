package com.floatingreels.sharpnote.util;

import androidx.room.TypeConverter;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converters {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @TypeConverter
    public static String dateToPattern(LocalDate localDate) {
        String datePattern = localDate.format(formatter);
        return localDate == null ? null : datePattern;
    }

    @TypeConverter
    public static LocalDate stringToDate(String datePattern){
        LocalDate localDate = LocalDate.parse(datePattern, formatter);
        return datePattern == null ? null : localDate;
    }
}
