package com.vetclinic.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vetclinic.demo.enums.EnApStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String animalName;
    private String diagnostic;
    private EnApStatus status;
    private double totalCost;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime appointmentDateTime;

    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID")
    @JsonIgnore
    private Doctor doctor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "appointment_services",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    @JsonIgnore
    private List<Service> services;




    public Appointment(String animalName, EnApStatus status, LocalDateTime appointmentDateTime, Doctor doctor, List<Service> services, Long totalCost) {
        this.animalName = animalName;
        this.status = status;
        this.appointmentDateTime = appointmentDateTime;
        this.doctor = doctor;
        this.services = services;
        this.totalCost = totalCost;
    }

    public Appointment() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public EnApStatus getStatus() {
        return status;
    }

    public void setStatus(EnApStatus status) {
        this.status = status;
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

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
