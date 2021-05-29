package com.vetclinic.demo.controller;

import com.vetclinic.demo.model.dto.ServiceDTO;
import com.vetclinic.demo.model.request.ServiceRequest;
import com.vetclinic.demo.service.ServicePersistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/service")

public class ServiceService implements ServiceController {

    @Autowired
    private ServicePersistanceService servicePersistanceService;

    @Override
    @PostMapping("/create")
    public ResponseEntity<ServiceDTO> createService(@RequestBody ServiceRequest serviceRequest) {
        ServiceDTO serviceDTO = servicePersistanceService.createService(serviceRequest);
        return new ResponseEntity<>(serviceDTO, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<ServiceDTO>> getAllServices() throws Exception {
        List<ServiceDTO> serviceDTOList = servicePersistanceService.findAll();
        return new ResponseEntity<>(serviceDTOList, HttpStatus.OK);
    }

    @Override
    @GetMapping("/all/{appointmentId}")
    public ResponseEntity<List<ServiceDTO>> getAllServicesFor(@PathVariable(value = "appointmentId") Long appointmentId) throws Exception {
        List<ServiceDTO> serviceDTOList = servicePersistanceService.getServicesFor(appointmentId);
        return new ResponseEntity<>(serviceDTOList, HttpStatus.OK);

    }
}
