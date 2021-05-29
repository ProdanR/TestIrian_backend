package com.vetclinic.demo.model.request;

import com.vetclinic.demo.enums.EnApStatus;
import com.vetclinic.demo.model.Doctor;
import com.vetclinic.demo.model.Service;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentRequest {

    private String animalName;

    private LocalDateTime appointmentDateTime;

    private Doctor doctor;

    private List<Service> services;


    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
