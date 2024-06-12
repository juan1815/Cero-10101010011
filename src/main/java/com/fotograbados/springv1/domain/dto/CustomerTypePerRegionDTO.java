package com.fotograbados.springv1.domain.dto;

import lombok.Data;

@Data
public class CustomerTypePerRegionDTO {
    private String regionName;
    private String customerType;
    private int customerCount;

}
