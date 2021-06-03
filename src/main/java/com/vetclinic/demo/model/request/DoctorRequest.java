package com.vetclinic.demo.model.request;

import javax.validation.constraints.NotEmpty;

public class DoctorRequest {
    @NotEmpty(message = "Doctor must have a name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
