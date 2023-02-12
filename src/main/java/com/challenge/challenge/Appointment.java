package com.challenge.challenge;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;


@TypeDefs({
        @TypeDef(
                name = "string-array",
                typeClass = StringArrayType.class
        )
})
@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Type(type = "text")
    @Column(name = "patient")
    private String patient;

    @Type(type = "text")
    @Column(name = "doctor")
    private String doctor;

    @Type(type = "text")
    @Column(name = "speciality")
    private String speciality;

    @Type(type = "text")
    @Column(name = "pathology")
    private String pathology;

    @Type(type = "string-array")
    @Column(name = "symptoms",
            columnDefinition = "text[]")
    private String[] symptoms;


    public Appointment(String patient, String doctor, String speciality, String pathology, String[] symptoms) {
        this.patient = patient;
        this.doctor = doctor;
        this.speciality = speciality;
        this.pathology = pathology;
        this.symptoms = symptoms;
    }

    public Appointment() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getPathology() {
        return pathology;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setPathology(String pathology) {
        this.pathology = pathology;
    }

    public String[] getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String[] symptoms) {
        this.symptoms = symptoms;
    }

}
