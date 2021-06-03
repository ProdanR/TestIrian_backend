package com.vetclinic.demo.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum EnApStatus {
    CREATED("created"),
    CONFIRMED("confirmed"),
    CLOSED("closed");

    private String status;

    private EnApStatus(String status) {
        this.status = status;
    }

    @JsonCreator
    public static EnApStatus decode(final String status){
        return Stream.of(EnApStatus.values()).filter(targetEnum -> targetEnum.status.equals(status)).findFirst().orElse(null);
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}
