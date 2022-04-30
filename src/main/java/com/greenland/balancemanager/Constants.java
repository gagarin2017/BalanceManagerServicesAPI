package com.greenland.balancemanager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public interface Constants {
	
    public static final String DATETIME_FORMAT = "dd-MM-yyyy HH:mm";
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    
    public static final LocalDateTime FIXED_DATE = LocalDateTime.now();
    public static LocalDateTimeSerializer LOCAL_DATETIME_SERIALIZER = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
    public static LocalDateSerializer LOCAL_DATE_SERIALIZER = new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT));
    public static LocalDateDeserializer LOCAL_DATE_DESERIALIZER = new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_FORMAT_YYYY_MM_DD));

}
