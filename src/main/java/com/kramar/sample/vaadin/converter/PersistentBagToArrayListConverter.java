package com.kramar.sample.vaadin.converter;

import com.vaadin.data.util.converter.Converter;

import java.util.*;

public final class PersistentBagToArrayListConverter implements Converter<List<String>, Object> {

    public static final PersistentBagToArrayListConverter PERSISTENT_BAG_TO_ARRAY_LIST_CONVERTER = new PersistentBagToArrayListConverter();

    private PersistentBagToArrayListConverter() {
    }

    @Override
    public Object convertToModel(List<String> strings, Class<?> aClass, Locale locale) throws ConversionException {
        return strings;
    }

    @Override
    public List<String> convertToPresentation(Object o, Class<? extends List<String>> aClass, Locale locale) throws ConversionException {
        return new ArrayList<>((Collection<? extends String>) o);
    }

    @Override
    public Class<Object> getModelType() {
        return Object.class;
    }

    @Override
    public Class<List<String>> getPresentationType() {
        return (Class<List<String>>) ((List<String>) new ArrayList<String>()).getClass();
    }
}
