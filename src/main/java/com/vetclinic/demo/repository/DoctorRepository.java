package com.vetclinic.demo.repository;

import com.vetclinic.demo.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}

