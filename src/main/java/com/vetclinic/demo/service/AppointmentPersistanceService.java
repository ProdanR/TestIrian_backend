package com.vetclinic.demo.service;

import com.vetclinic.demo.model.dto.AppointmentDTO;
import com.vetclinic.demo.model.request.AppointmentRequest;
import com.vetclinic.demo.model.request.DoctorRequest;


public interface AppointmentPersistanceService {
    AppointmentDTO createAppointment(AppointmentRequest appointmentRequest, Long doctorId) throws Exception;
}
