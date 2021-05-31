package com.vetclinic.demo.repository;

import com.vetclinic.demo.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
//    @Query("SELECT a FROM Appointment a ORDER BY a.appointmentDateTime DESC")
    List<Appointment> findByOrderByAppointmentDateTimeDesc();
}

