package com.soap.transport.soap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
public class SchemaConfig {
    @Bean
    public XsdSchema schema() {
        // TODO: move the path to properties
        return new SimpleXsdSchema(new ClassPathResource("xsd/request.xsd"));
    }
}
