package com.vetclinic.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vetclinic.demo.enums.EnApStatus;
import com.vetclinic.demo.model.Doctor;
import com.vetclinic.demo.model.Service;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class AppointmentDTO {
    private Long id;
    private String animalName;
    private String diagnostic;
    private EnApStatus status;
    private Doctor doctor;
    private List<Service> services;
    @JsonFormat(pattern = "yyyy-MM-ddThh:mm")
    private LocalDateTime appointmentDateTime;

    public AppointmentDTO(AppointmentDTO.BuilderAppointmentDTO builderAppointmentDTO) {
        this.id = builderAppointmentDTO.id;
        this.animalName = builderAppointmentDTO.animalName;
        this.diagnostic = builderAppointmentDTO.diagnostic;
        this.status = builderAppointmentDTO.status;
        this.doctor = builderAppointmentDTO.doctor;
        this.services = builderAppointmentDTO.services;
        this.appointmentDateTime = builderAppointmentDTO.appointmentDateTime;
    }


    //GETTERS
    public Long getId() {
        return id;
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public EnApStatus getStatus() {
        return status;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public List<Service> getServices() {
        return services;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }


    //BUILDER
    public static class BuilderAppointmentDTO {
        private Long id;
        private String animalName;
        private String diagnostic;
        private EnApStatus status;
        private Doctor doctor;
        private List<Service> services;
        @JsonFormat(pattern = "yyyy-MM-ddThh:mm")
        private LocalDateTime appointmentDateTime;

        public BuilderAppointmentDTO setId(Long id) {
            this.id = id;
            return this;
        }

        public BuilderAppointmentDTO setAnimalName(String animalName) {
            this.animalName = animalName;
            return this;
        }

        public BuilderAppointmentDTO setDiagnostic(String diagnostic) {
            this.diagnostic = diagnostic;
            return this;
        }

        public BuilderAppointmentDTO setStatus(EnApStatus status) {
            this.status = status;
            return this;
        }

        public BuilderAppointmentDTO setDoctor(Doctor doctor) {
            this.doctor = doctor;
            return this;
        }

        public BuilderAppointmentDTO setAppointmentDateTime(List<Service> services) {
            this.services = services;
            return this;
        }

        public BuilderAppointmentDTO setDoctor(LocalDateTime appointmentDateTime) {
            this.appointmentDateTime = appointmentDateTime;
            return this;
        }

        public AppointmentDTO build() {
            return new AppointmentDTO(this);
        }

    }


}
