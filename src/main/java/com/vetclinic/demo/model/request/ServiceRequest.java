package com.vetclinic.demo.model.request;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;


public class ServiceRequest {
    @NotEmpty(message = "Service must have a name")
    private String name;
    @DecimalMin("0.0")
    private double price;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
