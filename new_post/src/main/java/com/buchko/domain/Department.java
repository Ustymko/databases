package com.buchko.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Department {

    private Integer id;
    private Integer cityId;
    private String address;
    private Integer number;
}
