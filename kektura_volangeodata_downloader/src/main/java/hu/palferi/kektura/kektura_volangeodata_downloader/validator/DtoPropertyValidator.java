package hu.palferi.kektura.kektura_volangeodata_downloader.validator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DtoPropertyValidator {

    public void validateDtoFieldsAgainstEnum(Class<?> dtoClass, Class<? extends Enum<?>> headerEnumClass) {
        // 1. DTO mezőnevek sorrendben
        List<String> dtoFieldNames = Arrays.stream(dtoClass.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());

        try {
            // 2. Enum propertyName értékek sorrendben
            Method getPropertyNameMethod = headerEnumClass.getMethod("getPropertyName");
            List<String> enumPropertyNames = Arrays.stream(headerEnumClass.getEnumConstants())
                    .map(e -> {
                        try {
                            return (String) getPropertyNameMethod.invoke(e);
                        } catch (Exception ex) {
                            throw new RuntimeException("Hiba a propertyName lekérdezésekor: " + ex.getMessage(), ex);
                        }
                    })
                    .collect(Collectors.toList());

            // 3. Összehasonlítás
            if (!dtoFieldNames.equals(enumPropertyNames)) {
                throw new IllegalArgumentException("A DTO mezői nem egyeznek a várt enum property nevekkel!\n"
                        + "DTO mezők:    " + dtoFieldNames + "\n"
                        + "Enum mezők:   " + enumPropertyNames);
            }

        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Az enum nem tartalmaz getPropertyName() metódust: " + headerEnumClass.getName(), e);
        }
    }
}
