package com.challenge.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


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

    @GetMapping("getTopSpecialties")
    public ResponseEntity<List<TopSpeciality>> getTopSpecialties(){

        List<String> patientsCountedForSpeciality = new ArrayList<>();
        List<TopSpeciality> topSpecialities = new ArrayList<>();
        List<Appointment> appointments = appointmentRepository.findAll();
        for(Speciality speciality : Speciality.values()){
            String spec = speciality.getName();
            patientsCountedForSpeciality.clear();
           for(Appointment appointment : appointments){
               if(appointment.getSpeciality().getName().equals(spec)){
                   String patientName = appointment.getPatient();
                   if(patientsCountedForSpeciality.contains(patientName)){
                       continue;
                   }
                   patientsCountedForSpeciality.add(patientName);
               }
           }
            topSpecialities.add(new TopSpeciality(spec,patientsCountedForSpeciality.size()));
        }
        List<TopSpeciality> filteredTopSpecialist = topSpecialities.stream().
                filter( topSpeciality -> (topSpeciality.getNumberOfPatients() > 2 )).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(filteredTopSpecialist)){
            return ResponseEntity.ok().body(filteredTopSpecialist);
        }
        return ResponseEntity.notFound().build();

    }
}
