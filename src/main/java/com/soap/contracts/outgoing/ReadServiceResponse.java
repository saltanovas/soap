package com.soap.contracts.outgoing;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "Response", namespace = "test")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
public class ReadServiceResponse {
    @XmlElement(name = "Status", required = true)
    private ResponseStatus status;

    @XmlElement(name = "ErrorCode")
    private String errorCode;

    @XmlElement(name = "ErrorMessage")
    private String errorMessage;

    @XmlElement(name = "Service")
    private ServiceResponse service;

    public static ReadServiceResponse success(ServiceResponse service) {
        return new ReadServiceResponse(ResponseStatus.SUCCESS,  null, null, service);
    }

    public static ReadServiceResponse error(String errorCode, String errorMessage) {
        return new ReadServiceResponse(ResponseStatus.ERROR, errorCode, errorMessage, null);
    }
}
