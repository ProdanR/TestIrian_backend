package com.vetclinic.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vetclinic.demo.enums.EnApStatus;

import java.time.LocalDateTime;
import java.util.List;


public class AppointmentDTO {
    private Long id;
    private String animalName;
    private String diagnostic;
    private EnApStatus status;
    private DoctorDTO doctor;
    private List<ServiceDTO> services;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime appointmentDateTime;
    private double totalCost;


    public AppointmentDTO(AppointmentDTO.BuilderAppointmentDTO builderAppointmentDTO) {
        this.id = builderAppointmentDTO.id;
        this.animalName = builderAppointmentDTO.animalName;
        this.diagnostic = builderAppointmentDTO.diagnostic;
        this.status = builderAppointmentDTO.status;
        this.doctor = builderAppointmentDTO.doctor;
        this.services = builderAppointmentDTO.services;
        this.appointmentDateTime = builderAppointmentDTO.appointmentDateTime;
        this.totalCost = builderAppointmentDTO.totalCost;
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

    public DoctorDTO getDoctor() {
        return doctor;
    }

    public List<ServiceDTO> getServices() {
        return services;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public double getTotalCost() {
        return totalCost;
    }


    //BUILDER
    public static class BuilderAppointmentDTO {
        private Long id;
        private String animalName;
        private String diagnostic;
        private EnApStatus status;
        private DoctorDTO doctor;
        private List<ServiceDTO> services;
        @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
        private LocalDateTime appointmentDateTime;
        private double totalCost;

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

        public BuilderAppointmentDTO setDoctor(DoctorDTO doctor) {
            this.doctor = doctor;
            return this;
        }

        public BuilderAppointmentDTO setAppointmentDateTime(LocalDateTime appointmentDateTime) {
            this.appointmentDateTime = appointmentDateTime;
            return this;
        }

        public BuilderAppointmentDTO setServices(List<ServiceDTO> services) {
            this.services = services;
            return this;
        }

        public BuilderAppointmentDTO setTotalCost(double totalCost) {
            this.totalCost = totalCost;
            return this;
        }

        public AppointmentDTO build() {
            return new AppointmentDTO(this);
        }

    }


}
