package com.vetclinic.demo.service;

import com.vetclinic.demo.model.dto.AppointmentDTO;
import com.vetclinic.demo.model.request.AppointmentRequest;

import java.util.List;


public interface AppointmentPersistanceService {
    AppointmentDTO createAppointment(AppointmentRequest appointmentRequest, Long doctorId) throws Exception;
    List<AppointmentDTO> findAll() throws Exception;
    AppointmentDTO getAppointment(Long appointmentId) throws Exception;
    AppointmentDTO updateAppointment(AppointmentRequest appointmentRequest, Long appointmentId, Long doctorId) throws Exception;
}
