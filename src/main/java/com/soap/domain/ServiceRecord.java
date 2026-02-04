package com.soap.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ServiceRecord {
    String serviceId;
    String serviceType;
    String customerId;
    String subscriptionId;
    Boolean vipCustomer;
    ServiceDetails serviceDetails;
    CustomerDetails customerDetails;
}
