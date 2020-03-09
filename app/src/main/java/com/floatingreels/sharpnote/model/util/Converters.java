package com.floatingreels.sharpnote.model.util;

import androidx.room.TypeConverter;


import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

public class Converters {

    private static org.threeten.bp.format.DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @TypeConverter
    public static String dateToPattern(org.threeten.bp.LocalDate localDate) {
        String datePattern = localDate.format(formatter);
        return localDate == null ? null : datePattern;
    }

    @TypeConverter
    public static org.threeten.bp.LocalDate stringToDate(String datePattern){
        org.threeten.bp.LocalDate localDate = LocalDate.parse(datePattern, formatter);
        return datePattern == null ? null : localDate;
    }
}
