package com.soap.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ServiceDetails {
    String planType;
    String dataLimit;
    Boolean roamingEnabled;
    List<String> additionalServices;
    String specialOffer;
}
