package com.vetclinic.demo.service;

import com.vetclinic.demo.model.dto.AppointmentDTO;
import com.vetclinic.demo.model.dto.DoctorDTO;
import com.vetclinic.demo.model.request.AppointmentRequest;
import com.vetclinic.demo.model.request.DoctorRequest;

import java.util.List;


public interface AppointmentPersistanceService {
    AppointmentDTO createAppointment(AppointmentRequest appointmentRequest, Long doctorId) throws Exception;
    List<AppointmentDTO> findAll() throws Exception;
}
