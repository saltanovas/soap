package com.soap.contracts;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Address")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Address {
    @XmlElement(name = "Street")
    private String street;

    @XmlElement(name = "City")
    private String city;

    @XmlElement(name = "PostalCode")
    private String postalCode;

    @XmlElement(name = "Country")
    private String country;
}
