package com.buchko.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {

    private Integer id;
    private String name;
    private String surname;
    private String phoneNumber;

    @Override
    public String toString() {
        return this.id + ": " + this.name + " " + this.surname + " - phone number: " + this.phoneNumber;
    }
}
