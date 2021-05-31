package com.vetclinic.demo.service;

import com.vetclinic.demo.model.Service;
import com.vetclinic.demo.model.dto.ServiceDTO;
import com.vetclinic.demo.model.request.ServiceRequest;

import java.util.List;

public interface ServicePersistanceService {
    ServiceDTO createService(ServiceRequest serviceRequest);
    List<ServiceDTO> findAll() throws Exception;
    List<ServiceDTO> getServicesFor(Long appointmentId) throws Exception;
}
