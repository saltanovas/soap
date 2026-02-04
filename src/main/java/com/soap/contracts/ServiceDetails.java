package com.soap.contracts;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.*;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceDetails")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ServiceDetails {
    @XmlElement(name = "PlanType", required = true)
    String planType;

    @XmlElement(name = "DataLimit")
    String dataLimit;

    @XmlElement(name = "RoamingEnabled", required = true)
    Boolean roamingEnabled;

    @XmlElement(name = "AdditionalServices")
    List<String> additionalServices;
}
