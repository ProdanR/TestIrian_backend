package com.vetclinic.demo.service;

import com.vetclinic.demo.model.Doctor;
import com.vetclinic.demo.model.Service;
import com.vetclinic.demo.model.dto.DoctorDTO;
import com.vetclinic.demo.model.dto.ServiceDTO;
import com.vetclinic.demo.model.request.DoctorRequest;
import com.vetclinic.demo.repository.DoctorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<DoctorDTO> findAll() throws Exception {
        List<Doctor> doctorList = findDoctorsList();
        if (isDoctorEmptyList(doctorList)) {
            throw new Exception("Not services found in database");
        } else {
            return getResultList(doctorList);
        }
    }

    //return the list of doctorDTO
    private List<DoctorDTO> getResultList(List<Doctor> doctorList) {
        List<DoctorDTO> doctorDTOList = new ArrayList<DoctorDTO>();
        doctorList.forEach(doctor -> addDTOToList(doctorDTOList, doctor));
        return doctorDTOList;
    }

    private void addDTOToList(List<DoctorDTO> doctorDTOList, Doctor doctor) {
        DoctorDTO doctorDTO = buildDoctorDTO(doctor);
        doctorDTOList.add(doctorDTO);
    }

    private boolean isDoctorEmptyList(List<Doctor> doctorList) {
        if (doctorList.isEmpty())
            return true;
        return false;
    }

    private List<Doctor> findDoctorsList() {
        return doctorRepository.findAll();
    }


    private DoctorDTO buildDoctorDTO(Doctor doctor) {
        return new DoctorDTO.BuilderDoctorDTO()
                .setId(doctor.getId())
                .setName(doctor.getName())
                .build();
    }
}
