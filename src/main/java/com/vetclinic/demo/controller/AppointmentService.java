package com.vetclinic.demo.controller;

import com.vetclinic.demo.model.dto.AppointmentDTO;
import com.vetclinic.demo.model.dto.DoctorDTO;
import com.vetclinic.demo.model.request.AppointmentRequest;
import com.vetclinic.demo.service.AppointmentPersistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/appointment")
public class AppointmentService implements AppointmentController {

    @Autowired
    AppointmentPersistanceService appointmentPersistanceService;

    @Override
    @PostMapping("/create/{doctorId}")
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentRequest appointmentRequest, @PathVariable("doctorId") Long doctorId) throws Exception {
        AppointmentDTO appointmentDTO = appointmentPersistanceService.createAppointment(appointmentRequest,doctorId);
        return new ResponseEntity<>(appointmentDTO, HttpStatus.CREATED);
    }
}
