package com.soap.contracts.incoming;

import com.soap.contracts.CustomerDetails;
import com.soap.contracts.ServiceDetails;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequest {
    @XmlElement(name = "ServiceId", required = true)
    private String serviceId;

    @XmlElement(name = "ServiceType", required = true)
    private String serviceType;

    @XmlElement(name = "CustomerId", required = true)
    private String customerId;

    @XmlElement(name = "SubscriptionId", required = true)
    private String subscriptionId;

    @XmlElement(name = "ServiceDetails", required = true)
    private ServiceDetails serviceDetails;

    @XmlElement(name = "CustomerDetails", required = true)
    private CustomerDetails customerDetails;
}
