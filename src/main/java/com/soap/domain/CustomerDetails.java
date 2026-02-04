package com.soap.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerDetails {
    String name;
    Address address;
    String contactNumber;
}
