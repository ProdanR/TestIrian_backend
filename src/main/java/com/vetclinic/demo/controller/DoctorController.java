package com.vetclinic.demo.controller;

import com.vetclinic.demo.model.dto.DoctorDTO;
import com.vetclinic.demo.model.request.DoctorRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DoctorController {
    ResponseEntity<DoctorDTO> createDoctor(DoctorRequest doctorRequest);
    ResponseEntity<List<DoctorDTO>> getAllDoctors() throws Exception;
}
