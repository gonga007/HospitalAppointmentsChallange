package com.challenge.challenge;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    public List<Appointment> findByPatient(String patient);
}

