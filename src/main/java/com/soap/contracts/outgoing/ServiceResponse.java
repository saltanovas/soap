package com.soap.contracts.outgoing;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
public class ServiceResponse {
    @XmlElement(name = "ServiceId", required = true)
    private String serviceId;

    @XmlElement(name = "ServiceType", required = true)
    private String serviceType;

    @XmlElement(name = "CustomerId", required = true)
    private String customerId;

    @XmlElement(name = "SubscriptionId", required = true)
    private String subscriptionId;

    @XmlElement(name = "VipCustomer")
    private Boolean vipCustomer;

    @XmlElement(name = "ServiceDetails", required = true)
    private ServiceDetails serviceDetails;

    @XmlElement(name = "CustomerDetails", required = true)
    private CustomerDetails customerDetails;

    @XmlAccessorType(XmlAccessType.FIELD)
    @AllArgsConstructor
    public static class ServiceDetails {
        @XmlElement(name = "PlanType", required = true)
        private String planType;

        @XmlElement(name = "DataLimit")
        private String dataLimit;

        @XmlElement(name = "RoamingEnabled", required = true)
        private Boolean roamingEnabled;

        @XmlElement(name = "AdditionalServices", required = true)
        private List<String> additionalServices;

        @XmlElement(name = "SpecialOffer")
        private String specialOffer;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @AllArgsConstructor
    public static class CustomerDetails {
        @XmlElement(name = "Name", required = true)
        private String name;

        @XmlElement(name = "Address")
        private Address address;

        @XmlElement(name = "ContactNumber", required = true)
        private String contactNumber;


        @XmlAccessorType(XmlAccessType.FIELD)
        @AllArgsConstructor
        public static class Address {
            @XmlElement(name = "Street")
            private String street;

            @XmlElement(name = "City")
            private String city;

            @XmlElement(name = "PostalCode")
            private String postalCode;

            @XmlElement(name = "Country")
            private String country;
        }
    }
}
