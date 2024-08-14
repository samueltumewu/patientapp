package com.samuel.patientapp.util;

import java.lang.reflect.Field;

public class MyMapper {
    public static <D, E> void reflectionMapperDtoToEntity(D dto, E currEntity) {

        try {
            Field[] allDtoFields = dto.getClass().getDeclaredFields();

            for (Field dtoField : allDtoFields) {
                dtoField.setAccessible(true);
                Object value = dtoField.get(dto);
                if (value != null) {
                    Field entityField = currEntity.getClass().getDeclaredField(dtoField.getName());
                    entityField.setAccessible(true);
                    entityField.set(currEntity, value);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
