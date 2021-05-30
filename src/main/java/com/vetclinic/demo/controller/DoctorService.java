package com.vetclinic.demo.controller;

import com.vetclinic.demo.model.dto.DoctorDTO;
import com.vetclinic.demo.model.dto.ServiceDTO;
import com.vetclinic.demo.model.request.DoctorRequest;
import com.vetclinic.demo.service.DoctorPersistanceService;
import com.vetclinic.demo.service.ServicePersistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/doctor")

public class DoctorService implements DoctorController{

    @Autowired
    private DoctorPersistanceService doctorPersistanceService;

    @Override
    @PostMapping("/create")
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorRequest doctorRequest) {
        DoctorDTO doctorDTO = doctorPersistanceService.createDoctor(doctorRequest);
        return new ResponseEntity<>(doctorDTO, HttpStatus.CREATED);
    }
}
