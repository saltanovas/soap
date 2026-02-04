package com.soap.contracts;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerDetails")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerDetails {
    @XmlElement(name = "Name", required = true)
    String name;

    @XmlElement(name = "Address")
    Address address;

    @XmlElement(name = "ContactNumber", required = true)
    String contactNumber;
}
