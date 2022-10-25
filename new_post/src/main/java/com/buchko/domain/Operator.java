package com.buchko.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Operator {

    private Integer id;
    private Integer departmentId;
    private String name;
    private String surname;
    private String phoneNumber;
}
