package com.kramar.sample.vaadin.converter;

import com.vaadin.data.util.converter.Converter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;

public final class LocalDateToDateConverter implements Converter<Date, Object> {

    public static final LocalDateToDateConverter LOCAL_DATE_TO_DATE_CONVERTER = new LocalDateToDateConverter();

    private LocalDateToDateConverter() {}

    @Override
    public Object convertToModel(Date date, Class<?> aClass, Locale locale) throws ConversionException {
        return date == null ? null : ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }

    @Override
    public Date convertToPresentation(Object localDate, Class<? extends Date> aClass, Locale locale) throws ConversionException {
        return localDate == null ? null : Date.from(((LocalDate)localDate).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public Class<Object> getModelType() {
        return Object.class;
    }

    @Override
    public Class<Date> getPresentationType() {
        return Date.class;
    }

}
