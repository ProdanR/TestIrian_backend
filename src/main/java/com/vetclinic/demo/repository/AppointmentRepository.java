package com.vetclinic.demo.repository;

import com.vetclinic.demo.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByOrderByAppointmentDateTimeDesc();
}

