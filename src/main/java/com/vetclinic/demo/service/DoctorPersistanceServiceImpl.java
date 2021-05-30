package com.vetclinic.demo.service;

import com.vetclinic.demo.model.Doctor;
import com.vetclinic.demo.model.dto.DoctorDTO;
import com.vetclinic.demo.model.request.DoctorRequest;
import com.vetclinic.demo.repository.DoctorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class DoctorPersistanceServiceImpl implements DoctorPersistanceService{

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public DoctorDTO createDoctor(DoctorRequest doctorRequest) {
        Doctor doctor=new Doctor();
        BeanUtils.copyProperties(doctorRequest, doctor);
        Doctor saved = doctorRepository.save(doctor);
        return buildDoctorDTO(saved);
    }


    private DoctorDTO buildDoctorDTO(Doctor doctor) {
        return new DoctorDTO.BuilderDoctorDTO()
                .setId(doctor.getId())
                .setName(doctor.getName())
                .build();
    }
}
