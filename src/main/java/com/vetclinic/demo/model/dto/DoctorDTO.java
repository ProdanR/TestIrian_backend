package com.vetclinic.demo.model.dto;

public class DoctorDTO {
    private Long id;
    private String name;

    public DoctorDTO(DoctorDTO.BuilderDoctorDTO builderDoctorDTO) {
        this.id = builderDoctorDTO.id;
        this.name = builderDoctorDTO.name;
    }


    //GETTERS
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    //BUILDER
    public static class BuilderDoctorDTO {
        private Long id;
        private String name;

        public DoctorDTO.BuilderDoctorDTO setId(Long id) {
            this.id = id;
            return this;
        }

        public DoctorDTO.BuilderDoctorDTO setName(String name) {
            this.name = name;
            return this;
        }

        public DoctorDTO build() {
            return new DoctorDTO(this);
        }

    }
}
