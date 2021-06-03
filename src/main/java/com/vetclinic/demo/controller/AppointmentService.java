package com.vetclinic.demo.controller;

import com.vetclinic.demo.model.dto.AppointmentDTO;
import com.vetclinic.demo.model.request.AppointmentRequest;
import com.vetclinic.demo.service.AppointmentPersistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/appointment")
public class AppointmentService implements AppointmentController {

    @Autowired
    AppointmentPersistanceService appointmentPersistanceService;

    @Override
    @PostMapping("/create/{doctorId}")
    public ResponseEntity<AppointmentDTO> createAppointment(@Valid @RequestBody AppointmentRequest appointmentRequest, @PathVariable("doctorId") Long doctorId) {
        AppointmentDTO appointmentDTO = appointmentPersistanceService.createAppointment(appointmentRequest, doctorId);
        return new ResponseEntity<>(appointmentDTO, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<AppointmentDTO>> getAllAppoiments() {
        List<AppointmentDTO> appointmentDTOList = appointmentPersistanceService.findAll();
        return new ResponseEntity<>(appointmentDTOList, HttpStatus.OK);
    }

    @Override
    @GetMapping("/get/{appointmentId}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable("appointmentId") Long appointmentId) {
        AppointmentDTO appointmentDTO = appointmentPersistanceService.getAppointment(appointmentId);
        return new ResponseEntity<>(appointmentDTO, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/update/{appointmentId}/{doctorId}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@Valid @RequestBody AppointmentRequest appointmentRequest, @PathVariable("appointmentId") Long appointmentId, @PathVariable("doctorId") Long doctorId) {
        AppointmentDTO appointmentDTO = appointmentPersistanceService.updateAppointment(appointmentRequest, appointmentId, doctorId);
        return new ResponseEntity<>(appointmentDTO, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/delete/{appointmentId}")
    public boolean removeAppointment(@PathVariable("appointmentId") Long appointmentId) {
        return appointmentPersistanceService.removeAppointment(appointmentId);

    }
}
