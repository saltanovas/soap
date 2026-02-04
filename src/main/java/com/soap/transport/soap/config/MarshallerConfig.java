package com.soap.transport.soap.config;

import com.soap.contracts.outgoing.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class MarshallerConfig {
    @Bean
    public Jaxb2Marshaller createResponseMarshaller() {
        Jaxb2Marshaller m = new Jaxb2Marshaller();
        m.setClassesToBeBound(Response.class);
        return m;
    }
}
