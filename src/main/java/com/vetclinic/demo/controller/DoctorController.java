package com.vetclinic.demo.controller;

import com.vetclinic.demo.model.dto.DoctorDTO;
import com.vetclinic.demo.model.request.DoctorRequest;
import org.springframework.http.ResponseEntity;

public interface DoctorController {
    ResponseEntity<DoctorDTO> createDoctor(DoctorRequest doctorRequest);
}
