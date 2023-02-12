package com.challenge.challenge;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class SpecialityConverter implements AttributeConverter<Speciality, String> {
    @Override
    public String convertToDatabaseColumn(Speciality speciality) {
        if (speciality == null) {
            return null;
        }
        return speciality.getName();
    }

    @Override
    public Speciality convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        return Stream.of(Speciality.values()).filter(c -> c.getName().equals(s))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
