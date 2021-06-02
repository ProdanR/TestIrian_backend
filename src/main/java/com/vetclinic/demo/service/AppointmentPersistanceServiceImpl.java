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

import javax.persistence.EntityNotFoundException;
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
    public AppointmentDTO createAppointment(AppointmentRequest appointmentRequest, Long doctorId) {
        if (isDoctorPresent(doctorId)) {
            Appointment appointment = new Appointment();
            copyPropertiesFromAppoimentRequest(appointmentRequest, appointment);
            setDoctorToAppointment(appointment, doctorId);
            setServicesAndTotalCostToAppointment(appointment, appointmentRequest.getServicesId());
            setAppointmentStatusOnCreated(appointment);
            Appointment saved = saveAppointment(appointment);
            return buildAppointmentDTO(saved);
        } else {
            throw new EntityNotFoundException("Doctor with id:" + doctorId.toString() + " not found in the database");
        }
    }

    @Override
    public List<AppointmentDTO> findAll() {
        List<Appointment> appointmentList = findAppointmentList();
        if (isAppointmentEmptyList(appointmentList)) {
            throw new EntityNotFoundException("No appointments found in database");
        } else {
            return getResultList(appointmentList);
        }
    }

    @Override
    public AppointmentDTO getAppointment(Long appointmentId) {
        if (isAppointmentPresent(appointmentId)) {
            Appointment appointment = findAppointmentById(appointmentId);
            return buildAppointmentDTO(appointment);
        } else {
            throw new EntityNotFoundException("Appointment with id:" + appointmentId.toString() + " not found in the database");
        }

    }

    @Override
    public AppointmentDTO updateAppointment(AppointmentRequest appointmentRequest, Long appointmentId, Long doctorId) {
        if (isAppointmentPresent(appointmentId)) {
            Appointment appointment = findAppointmentById(appointmentId);
            copyPropertiesFromAppoimentRequest(appointmentRequest, appointment);
            setDoctorToAppointment(appointment, doctorId);
            setServicesAndTotalCostToAppointment(appointment, appointmentRequest.getServicesId());
            Appointment saved = saveAppointment(appointment);
            return buildAppointmentDTO(saved);
        } else {
            throw new EntityNotFoundException("Appointment with id:" + appointmentId.toString() + " not found in the database");
        }
    }


    //================================================================================
    // COPYING/SETTING PROPERTIES FROM REQUEST TO OBJECT
    //================================================================================
    private void copyPropertiesFromAppoimentRequest(AppointmentRequest appointmentRequest, Appointment appointment) {
        BeanUtils.copyProperties(appointmentRequest, appointment);
    }


    //================================================================================
    // SETTING STATUS,DOCTOR,SERVICES AND TOTALCOST TO APPOINTMENT
    //================================================================================
    private void setAppointmentStatusOnCreated(Appointment appointment) {
        appointment.setStatus(EnApStatus.CREATED);
    }

    private void setDoctorToAppointment(Appointment appointment, Long doctorId) {
        Doctor doctor = findDoctorById(doctorId);
        appointment.setDoctor(doctor);
    }

    private void setServicesAndTotalCostToAppointment(Appointment appointment, List<Long> servicesId) {
        List<Service> services = findServicesByIds(servicesId);
        appointment.setServices(services);
        double totalCost = calculateTotalCost(services);
        appointment.setTotalCost(totalCost);
    }

    private double calculateTotalCost(List<Service> services) {
        double totalCost = 0.0;
        for (Service service : services) {
            totalCost += service.getPrice();
        }
        return totalCost;
    }


    //================================================================================
    // BUILDING DTO FOR APPOINTMENT TO RETURN IT TO FRONTEND
    //================================================================================
    private List<AppointmentDTO> getResultList(List<Appointment> appointmentList) {
        List<AppointmentDTO> appointmentDTOList = new ArrayList<AppointmentDTO>();
        appointmentList.forEach(appointment -> addDTOToListAppointment(appointmentDTOList, appointment));
        return appointmentDTOList;
    }

    private void addDTOToListAppointment(List<AppointmentDTO> appointmentDTOList, Appointment appointment) {
        AppointmentDTO appointmentDTO = buildAppointmentDTO(appointment);
        appointmentDTOList.add(appointmentDTO);
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


    //================================================================================
    // BUILDING DTO FOR SERVICE TO SET THE FIELD OF APPOINTMENT_DTO
    //================================================================================
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


    //================================================================================
    // BUILDING DTO FOR DOCTOR TO SET THE FIELD OF APPOINTMENT_DTO
    //================================================================================
    private DoctorDTO buildDoctorDTO(Doctor doctor) {
        return new DoctorDTO.BuilderDoctorDTO()
                .setId(doctor.getId())
                .setName(doctor.getName())
                .build();
    }


    //================================================================================
    // CALL ServiceRepository FOR FIND & SAVE
    //================================================================================
    private Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    private Appointment findAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).get();
    }

    private List<Appointment> findAppointmentList() {
        return appointmentRepository.findByOrderByAppointmentDateTimeDesc();
    }

    private boolean isAppointmentPresent(Long appointmentId) {
        if (appointmentRepository.findById(appointmentId).isPresent())
            return true;
        return false;
    }


    //================================================================================
    // CALL ServiceRepository FOR FIND
    //================================================================================
    private List<Service> findServicesByIds(List<Long> servicesId) {
        return serviceRepository.findByIdIn(servicesId);
    }


    //================================================================================
    // CALL DoctorRepository FOR FIND
    //================================================================================
    private Doctor findDoctorById(Long doctorId) {
        return doctorRepository.getById(doctorId);
    }

    private boolean isDoctorPresent(Long doctorId) {
        if (doctorRepository.findById(doctorId).isPresent())
            return true;
        return false;
    }


    private boolean isAppointmentEmptyList(List<Appointment> appointmentList) {
        if (appointmentList.isEmpty())
            return true;
        return false;
    }


}
