package com.challenge.challenge.DTOs;

public class TopSpeciality {

    private String specialityName;
    private int numberOfPatients;

    public TopSpeciality(String specialityName, int numberOfPatients) {
        this.specialityName = specialityName;
        this.numberOfPatients = numberOfPatients;
    }
    public TopSpeciality(){

    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public int getNumberOfPatients() {
        return numberOfPatients;
    }

    public void setNumberOfPatients(int numberOfPatients) {
        this.numberOfPatients = numberOfPatients;
    }

}
