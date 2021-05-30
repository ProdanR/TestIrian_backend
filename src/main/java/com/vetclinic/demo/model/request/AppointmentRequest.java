package com.vetclinic.demo.model.request;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentRequest {

    private String animalName;

    private LocalDateTime appointmentDateTime;

    private List<Long> servicesId;


    public String getAnimalName() {
        return animalName;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public List<Long> getServices() {
        return servicesId;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public void setServices(List<Long> servicesId) {
        this.servicesId = servicesId;
    }
}
