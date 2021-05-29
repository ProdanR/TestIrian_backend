package com.vetclinic.demo.repository;

import com.vetclinic.demo.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service,Long> {
}
