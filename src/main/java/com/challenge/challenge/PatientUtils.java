package com.challenge.challenge;

import java.util.ArrayList;
import java.util.List;

public class PatientUtils {

    private static PatientUtils patientUtilsIstance = null;

    private PatientUtils(){

    }

    public static  PatientUtils getInstance(){
        if(patientUtilsIstance == null){
            patientUtilsIstance = new PatientUtils();
        }
        return patientUtilsIstance;
    }

    public List<Patient> appointmentsListToPatients(List<Appointment> appointments){
        List<Patient> patients = new ArrayList<>();

        for(Appointment appointment : appointments){
            Patient patient = appointmentToPatient(appointment);
            if(isPatientOnList(patient, patients)){
                continue;
            }
            patients.add(patient);
        }

        for (Patient patient : patients) {
            List<Appointment> patientAppointments = new ArrayList<>();

            for (Appointment appointment : appointments){
                if(appointment.getPatient().equals(patient.getNameWithAge())){
                    patientAppointments.add(appointment);
                }
                patient.setAppointments(patientAppointments);
            }
        }


        return patients;
    }
    private Patient appointmentToPatient(Appointment appointment){
        String patientName = appointment.getPatient().substring(0, appointment.getPatient().indexOf("(")).trim();
        String patientAge = appointment.getPatient().
                substring(appointment.getPatient().indexOf("(") + 1,
                        appointment.getPatient().indexOf(")")).trim();

        return new Patient(patientName,patientAge, null, appointment.getPatient());
    }
    private boolean isPatientOnList(Patient patient, List<Patient> patientList){
        return patientList.stream().anyMatch(p ->
                p.getAge().equals(patient.getAge()) && p.getName().equals(patient.getName()));
    }
}
