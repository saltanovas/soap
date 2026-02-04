package com.soap.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Address {
    String street;
    String city;
    String postalCode;
    String country;
}
