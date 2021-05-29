package com.vetclinic.demo.controller;

import com.vetclinic.demo.model.dto.ServiceDTO;
import com.vetclinic.demo.model.request.ServiceRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceController {
    ResponseEntity<ServiceDTO> createService(ServiceRequest serviceRequest);
    ResponseEntity<List<ServiceDTO>> getAllServices() throws Exception;
    ResponseEntity<List<ServiceDTO>> getAllServicesFor(Long appointmentId) throws Exception;
}
