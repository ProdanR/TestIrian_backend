package com.vetclinic.demo.controller;

import com.vetclinic.demo.model.dto.AppointmentDTO;

import com.vetclinic.demo.model.request.AppointmentRequest;
import org.springframework.http.ResponseEntity;

public interface AppointmentController {
    ResponseEntity<AppointmentDTO> createAppointment(AppointmentRequest appointmentRequest, Long doctorId) throws Exception;
}
