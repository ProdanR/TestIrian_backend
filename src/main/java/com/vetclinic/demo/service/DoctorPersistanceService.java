package com.vetclinic.demo.service;

import com.vetclinic.demo.model.Doctor;
import com.vetclinic.demo.model.dto.DoctorDTO;
import com.vetclinic.demo.model.request.DoctorRequest;

public interface DoctorPersistanceService {
    DoctorDTO createDoctor(DoctorRequest doctorRequest);

}
