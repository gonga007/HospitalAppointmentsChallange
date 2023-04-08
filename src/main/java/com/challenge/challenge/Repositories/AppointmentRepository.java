package com.challenge.challenge.Repositories;

import com.challenge.challenge.Entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    public List<Appointment> findByPatient(String patient);
}

