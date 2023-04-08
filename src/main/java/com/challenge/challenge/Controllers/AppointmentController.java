package com.challenge.challenge.Controllers;

import com.challenge.challenge.DTOs.Patient;
import com.challenge.challenge.DTOs.Speciality;
import com.challenge.challenge.DTOs.TopSpeciality;
import com.challenge.challenge.Entities.Appointment;
import com.challenge.challenge.Repositories.AppointmentRepository;
import com.challenge.challenge.Utils.Page;
import com.challenge.challenge.Utils.PatientUtils;
import com.challenge.challenge.Errors.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RequestMapping("/api/appointments")
@RestController
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @PostMapping
    public Appointment createAppointment(@Validated @RequestBody Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return (List<Appointment>) appointmentRepository.findAll();
    }

    @GetMapping("/getPatientConsultsAndSymptoms/{patientName}")
    public ResponseEntity<HashMap<String, List<Appointment>>> getPatientConsultsAndSymptoms(@PathVariable(value = "patientName") String patientName) {
        List<Appointment> appointments = appointmentRepository.findByPatient(patientName);

        HashMap<String, List<Appointment>> consultsMap = new HashMap<>();
        consultsMap.put("consults", appointments);
        if (CollectionUtils.isEmpty(appointments)) {
            throw new NotFoundException("There are not appointments for patient: " + patientName, "Appointment");
        }
        return ResponseEntity.ok().body(consultsMap);
    }

    @GetMapping("getTopSpecialties")
    public ResponseEntity<List<TopSpeciality>> getTopSpecialties() {

        List<String> patientsCountedForSpeciality = new ArrayList<>();
        List<TopSpeciality> topSpecialities = new ArrayList<>();
        List<Appointment> appointments = appointmentRepository.findAll();
        for (Speciality speciality : Speciality.values()) {
            String spec = speciality.getName();
            patientsCountedForSpeciality.clear();
            for (Appointment appointment : appointments) {
                if (appointment.getSpeciality().getName().equals(spec)) {
                    String patientName = appointment.getPatient();
                    if (patientsCountedForSpeciality.contains(patientName)) {
                        continue;
                    }
                    patientsCountedForSpeciality.add(patientName);
                }
            }
            topSpecialities.add(new TopSpeciality(spec, patientsCountedForSpeciality.size()));
        }
        List<TopSpeciality> filteredTopSpecialist = topSpecialities.stream().
                filter(topSpeciality -> (topSpeciality.getNumberOfPatients() > 2)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(filteredTopSpecialist)) {
            throw new NotFoundException("There are no Top Specialites","Top Specialties");
        }
        return ResponseEntity.ok().body(filteredTopSpecialist);

    }

    @GetMapping("/getAllPatients")
    public ResponseEntity<Page<Patient>> getAllPatients(@RequestParam(name = "patientName", required = false) String patientName,
                                                        @RequestParam(name = "patientAge", required = false) Integer patientAge,
                                                        @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
                                                        @RequestParam(name = "pageRange", required = false) Integer pageRange,
                                                        @RequestParam(name = "resultsPerPage", required = false) Integer resultsPerPage) {


        List<Appointment> appointments = getAllAppointments();
        if (CollectionUtils.isEmpty(appointments)) {
            throw new NotFoundException("There are no appointments","Appointment");
        }
        List<Patient> patients = PatientUtils.getInstance().appointmentsListToPatients(appointments);

        List<Patient> filteredPatients = patients.stream().filter(p -> {
            if (patientAge != null && patientAge != 0l) {
                return p.getAge().equals(String.valueOf(patientAge));
            } else if (patientName != null) {
                return p.getName().equals(patientName);
            }
            return true;
        }).collect(Collectors.toList());

        if (patientAge != null && patientAge != 0L) {
            patients.sort(Comparator.comparing(patient -> Integer.valueOf(patient.getAge())));
        } else if (patientName != null) {
            patients.sort(Comparator.comparing(Patient::getName));
        }

        if (CollectionUtils.isEmpty(filteredPatients)) {
            throw new NotFoundException("Patient not found for PatientName= " +
                    patientName + ", PatientAge= " + patientAge, "Patient");
        }
        if (pageRange == null || pageRange == 0L) {
            pageRange = 25;
        }
        if (pageNumber == null || pageNumber == 0L) {
            pageNumber = 1;
        }
        if (resultsPerPage == null || resultsPerPage == 0l) {
            resultsPerPage = 25;
        }
        Page<List<Patient>> patientsPaginatedList = new Page(pageNumber, pageRange, patients, resultsPerPage);
        return ResponseEntity.ok().body(patientsPaginatedList.getPage(patients, pageNumber, resultsPerPage));
    }
}
