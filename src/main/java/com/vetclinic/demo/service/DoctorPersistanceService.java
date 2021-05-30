package com.vetclinic.demo.service;

import com.vetclinic.demo.model.Doctor;
import com.vetclinic.demo.model.dto.DoctorDTO;
import com.vetclinic.demo.model.request.DoctorRequest;

import java.util.List;

public interface DoctorPersistanceService {
    DoctorDTO createDoctor(DoctorRequest doctorRequest);
    List<DoctorDTO> findAll() throws Exception;
}
