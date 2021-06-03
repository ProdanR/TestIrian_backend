package com.vetclinic.demo.service;

import com.vetclinic.demo.model.Doctor;
import com.vetclinic.demo.model.dto.DoctorDTO;
import com.vetclinic.demo.model.request.DoctorRequest;
import com.vetclinic.demo.repository.DoctorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class DoctorPersistanceServiceImpl implements DoctorPersistanceService {

    @Autowired
    DoctorRepository doctorRepository;


    @Override
    public DoctorDTO createDoctor(DoctorRequest doctorRequest) {
        Doctor doctor = new Doctor();
        copyPropertiesFromDoctorRequest(doctorRequest, doctor);
        Doctor saved = saveDoctor(doctor);
        return buildDoctorDTO(saved);
    }

    @Override
    public List<DoctorDTO> findAll() {
        List<Doctor> doctorList = findDoctorsList();
        if (isDoctorEmptyList(doctorList)) {
            throw new EntityNotFoundException("No doctors found in database");
        } else {
            return getResultList(doctorList);
        }
    }


    //================================================================================
    // COPYING PROPERTIES FROM REQUEST TO OBJECT
    //================================================================================
    private void copyPropertiesFromDoctorRequest(DoctorRequest doctorRequest, Doctor doctor) {
        BeanUtils.copyProperties(doctorRequest, doctor);
    }


    //================================================================================
    // BUILDING DTO FOR DOCTOR TO RETURN IT TO FRONTEND
    //================================================================================
    private List<DoctorDTO> getResultList(List<Doctor> doctorList) {
        List<DoctorDTO> doctorDTOList = new ArrayList<DoctorDTO>();
        doctorList.forEach(doctor -> addDTOToList(doctorDTOList, doctor));
        return doctorDTOList;
    }

    private void addDTOToList(List<DoctorDTO> doctorDTOList, Doctor doctor) {
        DoctorDTO doctorDTO = buildDoctorDTO(doctor);
        doctorDTOList.add(doctorDTO);
    }

    private DoctorDTO buildDoctorDTO(Doctor doctor) {
        return new DoctorDTO.BuilderDoctorDTO()
                .setId(doctor.getId())
                .setName(doctor.getName())
                .build();
    }


    //================================================================================
    // CALL ServiceRepository FOR FIND & SAVE
    //================================================================================
    private List<Doctor> findDoctorsList() {
        return doctorRepository.findAll();
    }

    private Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }


    private boolean isDoctorEmptyList(List<Doctor> doctorList) {
        if (doctorList.isEmpty())
            return true;
        return false;
    }

}
