package com.challenge.challenge;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RequestMapping("/api/appointments")
@RestController
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @PostMapping
    public Appointment createAppointment(@Validated @RequestBody Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    @GetMapping
    public List<Appointment> getAllAppointments(){
        return (List<Appointment>) appointmentRepository.findAll();
    }

    @GetMapping("/getPatientConsultsAndSymptoms/{patientName}")
    public ResponseEntity<HashMap<String, List<Appointment>>> getPatientConsultsAndSymptoms(@PathVariable(value = "patientName") String patientName){
        List<Appointment> appointments = appointmentRepository.findByPatient(patientName);

        HashMap<String, List<Appointment>> consultsMap = new HashMap<>();
        consultsMap.put("consults", appointments);
        if(!CollectionUtils.isEmpty(appointments)){
            return ResponseEntity.ok().body(consultsMap);
        }
        return ResponseEntity.notFound().build();
    }
}
