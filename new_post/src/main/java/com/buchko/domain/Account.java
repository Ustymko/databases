package com.buchko.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {

    private Integer clientId;
    private String username;
    private String password;
    private String emailAddress;
}
