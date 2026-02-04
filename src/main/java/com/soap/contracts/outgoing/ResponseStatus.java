package com.soap.contracts.outgoing;

import jakarta.xml.bind.annotation.XmlEnumValue;

public enum ResponseStatus {
    @XmlEnumValue("Success")
    SUCCESS,

    @XmlEnumValue("Error")
    ERROR
}
