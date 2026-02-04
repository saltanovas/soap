package com.soap.contracts.incoming;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "Create", namespace = "test")
@XmlAccessorType(XmlAccessType.FIELD)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CreateServiceRequest extends ServiceRequest {
}
