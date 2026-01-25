package com.backend.happyhome.dtos.admin_dto;

import java.util.List;

import com.backend.happyhome.entities.enums.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDtoD {

    private Long serviceId;
    private String serviceName;
    private String shortDesc;
    private String longDesc;
    private Double price;
    private Category category;

    private List<ServiceImageDtoD> images;
}
