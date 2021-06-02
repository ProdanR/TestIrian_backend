package com.vetclinic.demo.service;

import com.vetclinic.demo.model.dto.AppointmentDTO;
import com.vetclinic.demo.model.request.AppointmentRequest;

import java.util.List;


public interface AppointmentPersistanceService {
    AppointmentDTO createAppointment(AppointmentRequest appointmentRequest, Long doctorId);
    List<AppointmentDTO> findAll();
    AppointmentDTO getAppointment(Long appointmentId);
    AppointmentDTO updateAppointment(AppointmentRequest appointmentRequest, Long appointmentId, Long doctorId);
}
