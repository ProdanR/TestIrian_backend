package com.vetclinic.demo.service;

import com.vetclinic.demo.model.Appointment;
import com.vetclinic.demo.model.Service;
import com.vetclinic.demo.model.dto.ServiceDTO;
import com.vetclinic.demo.model.request.ServiceRequest;
import com.vetclinic.demo.repository.AppointmentRepository;
import com.vetclinic.demo.repository.ServiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class ServicePersistanceServiceImpl implements ServicePersistanceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public ServiceDTO createService(ServiceRequest serviceRequest) {
        Service service = new Service();
        BeanUtils.copyProperties(serviceRequest, service);
        Service saved = serviceRepository.save(service);
        return buildServiceDTO(saved);
    }

    @Override
    public List<ServiceDTO> findAll() throws Exception {
        List<Service> serviceList = findServiceList();
        if (isServiceEmptyList(serviceList)) {
            throw new Exception("Not services found in database");
        } else {
            return getResultList(serviceList);
        }
    }

    @Override
    public List<ServiceDTO> getServicesFor(Long appointmentId) throws Exception {
        if (isAppointmentPresent(appointmentId)) {
            Appointment appointment = findAppintmentById(appointmentId);
            List<Service> services = appointment.getServices();
            if (isServiceEmptyList(services)) {
                throw new RuntimeException("The appointment doesn't have services");
            } else {
                return getResultList(services);
            }
        } else {
            throw new Exception("The appointment doesn't exist");
        }
    }

    private Appointment findAppintmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).get();
    }

    private boolean isAppointmentPresent(Long appointmentId) {
        if (appointmentRepository.findById(appointmentId).isPresent())
            return true;
        return false;
    }

    private List<ServiceDTO> getResultList(List<Service> serviceList) {
        List<ServiceDTO> serviceDTOList = new ArrayList<ServiceDTO>();
        serviceList.forEach(service -> addDTOToList(serviceDTOList, service));
        return serviceDTOList;
    }

    private void addDTOToList(List<ServiceDTO> serviceDTOList, Service service) {
        ServiceDTO serviceDTO = buildServiceDTO(service);
        serviceDTOList.add(serviceDTO);
    }

    private boolean isServiceEmptyList(List<Service> serviceList) {
        if (serviceList.isEmpty())
            return true;
        return false;
    }


    private List<Service> findServiceList() {
        return serviceRepository.findAll();
    }


    private ServiceDTO buildServiceDTO(Service service) {
        return new ServiceDTO.BuilderServiceDTO()
                .setId(service.getId())
                .setName(service.getName())
                .setPrice(service.getPrice())
                .build();
    }


}
