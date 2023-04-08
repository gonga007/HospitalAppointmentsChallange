package com.challenge.challenge.DTOs;

import com.challenge.challenge.Entities.Appointment;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Patient implements Person{

    private String name;
    private String age;
    private List<Appointment> appointments;
    @JsonIgnore
    private String nameWithAge;


    public Patient(String name, String age, List<Appointment> appointments, String nameWithAge) {
        this.name = name;
        this.age = age;
        this.appointments = appointments;
        this.nameWithAge = nameWithAge;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
    public String getNameWithAge() {
        return nameWithAge;
    }

    public void setNameWithAge(String nameWithAge) {
        this.nameWithAge = nameWithAge;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
