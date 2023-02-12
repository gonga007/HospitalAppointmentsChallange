package com.challenge.challenge;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Speciality {
    DERMATOLOGY("Dermatology"),
    OPHTHALMOLOGY("Ophthalmology"),
    RADIOLOGY("Radiology"),
    FAMILY_MEDICINE("Family Medicine"),
    PEDIATRICS("Pediatrics");

    private final String name;

    Speciality(String name){
        this.name = name;
    }
    @JsonValue
    public String getName(){
        return this.name;
    }

}
