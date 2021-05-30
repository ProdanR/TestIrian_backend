package com.vetclinic.demo.service;

import com.vetclinic.demo.enums.EnApStatus;
import com.vetclinic.demo.model.Appointment;
import com.vetclinic.demo.model.Doctor;
import com.vetclinic.demo.model.Service;
import com.vetclinic.demo.model.dto.AppointmentDTO;
import com.vetclinic.demo.model.dto.DoctorDTO;
import com.vetclinic.demo.model.dto.ServiceDTO;
import com.vetclinic.demo.model.request.AppointmentRequest;
import com.vetclinic.demo.model.request.DoctorRequest;
import com.vetclinic.demo.repository.AppointmentRepository;
import com.vetclinic.demo.repository.DoctorRepository;
import com.vetclinic.demo.repository.ServiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class AppointmentPersistanceServiceImpl implements AppointmentPersistanceService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    DoctorPersistanceServiceImpl doctorPersistanceService;

    @Autowired
    ServicePersistanceServiceImpl servicePersistanceService;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public AppointmentDTO createAppointment(AppointmentRequest appointmentRequest, Long doctorId) throws Exception {

        if(isDoctorPresent(doctorId))
        {
            Doctor doctor= doctorRepository.getById(doctorId);
            Appointment appointment = new Appointment();
            BeanUtils.copyProperties(appointmentRequest, appointment);
            setAppointmentStatusOnCreated(appointment);
            appointment.setDoctor(doctor);
            List<Service> services= serviceRepository.findByIdIn(appointmentRequest.getServices());
            appointment.setServices(services);
            appointment.setTotalCost(calculateTotalCost(services));
            Appointment saved = appointmentRepository.save(appointment);
            return buildAppointmentDTO(saved);
        }
        else
        {
            throw new Exception("Doctor doesn't exist");
        }

    }

    private double calculateTotalCost(List<Service> services) {
        double totalCost=0.0;
        for (Service service: services) {
            totalCost+=service.getPrice();
        }
        return totalCost;
    }

    private boolean isDoctorPresent(Long doctorId) {
        if (doctorRepository.findById(doctorId).isPresent())
            return true;
        return false;
    }

    private void setAppointmentStatusOnCreated(Appointment appointment) {
        appointment.setStatus(EnApStatus.CREATED);
    }

    private AppointmentDTO buildAppointmentDTO(Appointment appointment) {
        DoctorDTO doctorDTO = buildDoctorDTO(appointment.getDoctor());
        List<ServiceDTO> serviceDTOList = buildServiceDTOList(appointment.getServices());
        return new AppointmentDTO.BuilderAppointmentDTO()
                .setId(appointment.getId())
                .setAnimalName(appointment.getAnimalName())
                .setDoctor(doctorDTO)
                .setAppointmentDateTime(appointment.getAppointmentDateTime())
                .setDiagnostic(appointment.getDiagnostic())
                .setStatus(appointment.getStatus())
                .setServices(serviceDTOList)
                .setTotalCost(appointment.getTotalCost())
                .build();
    }

    private List<ServiceDTO> buildServiceDTOList(List<Service> services) {
        List<ServiceDTO> serviceDTOList = new ArrayList<ServiceDTO>();
        services.forEach(service -> addDTOToList(serviceDTOList, service));
        return serviceDTOList;
    }

    private void addDTOToList(List<ServiceDTO> serviceDTOList, Service service) {
        ServiceDTO serviceDTO = buildServiceDTO(service);
        serviceDTOList.add(serviceDTO);
    }

    private ServiceDTO buildServiceDTO(Service service) {
        return new ServiceDTO.BuilderServiceDTO()
                .setId(service.getId())
                .setPrice(service.getPrice())
                .setName(service.getName())
                .build();
    }

    private DoctorDTO buildDoctorDTO(Doctor doctor) {
        return new DoctorDTO.BuilderDoctorDTO()
                .setId(doctor.getId())
                .setName(doctor.getName())
                .build();
    }
}
