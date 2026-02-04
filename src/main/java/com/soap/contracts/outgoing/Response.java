package com.soap.contracts.outgoing;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.*;

@XmlRootElement(name = "Response")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Response {
    @XmlElement(name = "Status", required = true)
    private ResponseStatus status;

    @XmlElement(name = "Message")
    private String message;

    @XmlElement(name = "ErrorCode")
    private String errorCode;

    @XmlElement(name = "ErrorMessage")
    private String errorMessage;

    public static Response success(String message) {
        return new Response(ResponseStatus.SUCCESS, message, null, null);
    }

    public static Response error(String errorCode, String errorMessage) {
        return new Response(ResponseStatus.ERROR, null, errorCode, errorMessage);
    }
}
