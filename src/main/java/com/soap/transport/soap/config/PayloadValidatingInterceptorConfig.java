package com.soap.transport.soap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
public class PayloadValidatingInterceptorConfig {
    @Bean
    public SoapPayloadValidatingInterceptor soapPayloadValidatingInterceptor(
            XsdSchema schema,
            Jaxb2Marshaller marshaller
    ) {
        SoapPayloadValidatingInterceptor i = new SoapPayloadValidatingInterceptor(marshaller);
        i.setXsdSchema(schema);
        i.setValidateRequest(true);
        i.setValidateResponse(false);
        i.setAddValidationErrorDetail(false);
        return i;
    }
}
