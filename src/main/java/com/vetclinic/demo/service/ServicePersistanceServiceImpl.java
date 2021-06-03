package com.vetclinic.demo.service;

import com.vetclinic.demo.model.Appointment;
import com.vetclinic.demo.model.Service;
import com.vetclinic.demo.model.dto.ServiceDTO;
import com.vetclinic.demo.model.request.ServiceRequest;
import com.vetclinic.demo.repository.AppointmentRepository;
import com.vetclinic.demo.repository.ServiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
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
        copyPropertiesFromServiceRequest(serviceRequest, service);
        Service saved = saveService(service);
        return buildServiceDTO(saved);
    }

    @Override
    public List<ServiceDTO> findAll() {
        List<Service> serviceList = findServiceList();
        if (isServiceEmptyList(serviceList)) {
            throw new EntityNotFoundException("No services found in database");
        } else {
            return getResultList(serviceList);
        }
    }

    @Override
    public List<ServiceDTO> getServicesFor(Long appointmentId) {
        if (isAppointmentPresent(appointmentId)) {
            Appointment appointment = findAppintmentById(appointmentId);
            List<Service> services = appointment.getServices();
            if (isServiceEmptyList(services)) {
                throw new EntityNotFoundException("Appointment with id:" + appointmentId.toString() + " not does't have any services");
            } else {
                return getResultList(services);
            }
        } else {
            throw new EntityNotFoundException("Appointment with id:" + appointmentId.toString() + " not found in the database");
        }
    }


    //================================================================================
    // COPYING PROPERTIES FROM REQUEST TO OBJECT
    //================================================================================
    private void copyPropertiesFromServiceRequest(ServiceRequest serviceRequest, Service service) {
        BeanUtils.copyProperties(serviceRequest, service);
    }


    //================================================================================
    // BUILDING DTO FOR SERVICE TO RETURN IT TO FRONTEND
    //================================================================================
    private List<ServiceDTO> getResultList(List<Service> serviceList) {
        List<ServiceDTO> serviceDTOList = new ArrayList<ServiceDTO>();
        serviceList.forEach(service -> addDTOToList(serviceDTOList, service));
        return serviceDTOList;
    }

    private void addDTOToList(List<ServiceDTO> serviceDTOList, Service service) {
        ServiceDTO serviceDTO = buildServiceDTO(service);
        serviceDTOList.add(serviceDTO);
    }

    private ServiceDTO buildServiceDTO(Service service) {
        return new ServiceDTO.BuilderServiceDTO()
                .setId(service.getId())
                .setName(service.getName())
                .setPrice(service.getPrice())
                .build();
    }


    //================================================================================
    // CALL ServiceRepository FOR FIND & SAVE
    //================================================================================
    private List<Service> findServiceList() {
        return serviceRepository.findAll();
    }

    private Appointment findAppintmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).get();
    }

    private Service saveService(Service service) {
        return serviceRepository.save(service);
    }

    private boolean isAppointmentPresent(Long appointmentId) {
        if (appointmentRepository.findById(appointmentId).isPresent())
            return true;
        return false;
    }


    private boolean isServiceEmptyList(List<Service> serviceList) {
        if (serviceList.isEmpty())
            return true;
        return false;
    }




}
