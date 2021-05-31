package com.vetclinic.demo.service;

import com.vetclinic.demo.enums.EnApStatus;
import com.vetclinic.demo.model.Appointment;
import com.vetclinic.demo.model.Doctor;
import com.vetclinic.demo.model.Service;
import com.vetclinic.demo.model.dto.AppointmentDTO;
import com.vetclinic.demo.model.dto.DoctorDTO;
import com.vetclinic.demo.model.dto.ServiceDTO;
import com.vetclinic.demo.model.request.AppointmentRequest;
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

        if (isDoctorPresent(doctorId)) {

            Appointment appointment = new Appointment();
            copyPropertiesFromAppoimentRequest(appointmentRequest, appointment, doctorId);
            setAppointmentStatusOnCreated(appointment);
            Appointment saved = appointmentRepository.save(appointment);
            return buildAppointmentDTO(saved);
        } else {
            throw new Exception("Doctor doesn't exist");
        }

    }


    @Override
    public List<AppointmentDTO> findAll() throws Exception {
        List<Appointment> appointmentList = findAppointmentList();
        if (isAppointmentEmptyList(appointmentList)) {
            throw new Exception("Not appointments found in database");
        } else {
            return getResultList(appointmentList);
        }
    }

    @Override
    public AppointmentDTO getAppointment(Long appointmentId) throws Exception {
        if (isAppointmentPresent(appointmentId)) {
            Appointment appointment = findAppointmentById(appointmentId);
            return buildAppointmentDTO(appointment);
        } else {
            throw new Exception("Appointment doesn't exist");
        }

    }

    @Override
    public AppointmentDTO updateAppointment(AppointmentRequest appointmentRequest, Long appointmentId, Long doctorId) throws Exception {
        if (isAppointmentPresent(appointmentId)) {
            Appointment appointment = findAppointmentById(appointmentId);
            copyPropertiesFromAppoimentRequest(appointmentRequest, appointment, doctorId);
            Appointment saved = appointmentRepository.save(appointment);
            return buildAppointmentDTO(saved);
        } else {
            throw new Exception("Appointment doen't exist");
        }
    }

    private void copyPropertiesFromAppoimentRequest(AppointmentRequest appointmentRequest, Appointment appointment, Long doctorId) {
        BeanUtils.copyProperties(appointmentRequest, appointment);
        Doctor doctor = doctorRepository.getById(doctorId);
        appointment.setDoctor(doctor);
        List<Service> services = serviceRepository.findByIdIn(appointmentRequest.getServices());
        appointment.setServices(services);
        appointment.setTotalCost(calculateTotalCost(services));
    }


    private boolean isAppointmentPresent(Long appointmentId) {
        if (appointmentRepository.findById(appointmentId).isPresent())
            return true;
        return false;
    }

    private Appointment findAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).get();
    }


    private List<Appointment> findAppointmentList() {
        return appointmentRepository.findByOrderByAppointmentDateTimeDesc();
    }

    private boolean isAppointmentEmptyList(List<Appointment> appointmentList) {
        if (appointmentList.isEmpty())
            return true;
        return false;
    }

    private List<AppointmentDTO> getResultList(List<Appointment> appointmentList) {
        List<AppointmentDTO> appointmentDTOList = new ArrayList<AppointmentDTO>();
        appointmentList.forEach(appointment -> addDTOToListAppointment(appointmentDTOList, appointment));
        return appointmentDTOList;
    }

    private void addDTOToListAppointment(List<AppointmentDTO> appointmentDTOList, Appointment appointment) {
        AppointmentDTO appointmentDTO = buildAppointmentDTO(appointment);
        appointmentDTOList.add(appointmentDTO);
    }


    private double calculateTotalCost(List<Service> services) {
        double totalCost = 0.0;
        for (Service service : services) {
            totalCost += service.getPrice();
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
