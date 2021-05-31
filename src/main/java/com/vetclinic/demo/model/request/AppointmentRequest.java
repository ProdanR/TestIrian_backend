package com.vetclinic.demo.model.request;

import com.vetclinic.demo.enums.EnApStatus;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentRequest {

    private String animalName;

    private LocalDateTime appointmentDateTime;

    private List<Long> servicesId;

    private String diagnostic;

    public List<Long> getServicesId() {
        return servicesId;
    }

    public void setServicesId(List<Long> servicesId) {
        this.servicesId = servicesId;
    }

    public EnApStatus getStatus() {
        return status;
    }

    public void setStatus(EnApStatus status) {
        this.status = status;
    }

    private EnApStatus status;

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

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }


}
