package com.vetclinic.demo.repository;

import com.vetclinic.demo.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findByIdIn(List<Long> serviceId);
}
