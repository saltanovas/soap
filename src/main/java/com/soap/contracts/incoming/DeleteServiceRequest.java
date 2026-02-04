package com.soap.contracts.incoming;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "Delete", namespace = "test")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteServiceRequest {
    @XmlElement(name = "ServiceId", required = true)
    private String serviceId;
}
