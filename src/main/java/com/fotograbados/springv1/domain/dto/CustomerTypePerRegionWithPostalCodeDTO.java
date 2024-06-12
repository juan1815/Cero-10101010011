package com.fotograbados.springv1.domain.dto;

import lombok.Data;

@Data
public class CustomerTypePerRegionWithPostalCodeDTO {
    private String regionName;
    private String customerType;
    private String postalCode;
    private int customerCount;
}
