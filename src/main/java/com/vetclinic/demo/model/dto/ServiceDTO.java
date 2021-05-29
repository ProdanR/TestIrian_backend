package com.vetclinic.demo.model.dto;

public class ServiceDTO {
    private Long id;
    private String name;
    private Long price;


    public ServiceDTO(BuilderServiceDTO builderServiceDTO) {
        this.id = builderServiceDTO.id;
        this.name = builderServiceDTO.name;
        this.price = builderServiceDTO.price;
    }


    //GETTERS
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }


    //BUILDER
    public static class BuilderServiceDTO {
        private Long id;
        private String name;
        private Long price;

        public BuilderServiceDTO setId(Long id) {
            this.id = id;
            return this;
        }

        public BuilderServiceDTO setName(String name) {
            this.name = name;
            return this;
        }

        public BuilderServiceDTO setPrice(Long price) {
            this.price = price;
            return this;
        }

        public ServiceDTO build() {
            return new ServiceDTO(this);
        }

    }
}
