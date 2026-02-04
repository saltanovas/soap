package com.soap.transport.soap.config;

import com.soap.contracts.outgoing.Response;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.xml.sax.SAXParseException;

import javax.xml.transform.TransformerException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SoapPayloadValidatingInterceptor extends PayloadValidatingInterceptor {
    private final Jaxb2Marshaller marshaller;

    @Override
    protected boolean handleRequestValidationErrors(
            @NonNull MessageContext messageContext,
            SAXParseException @NonNull [] errors
    ) throws TransformerException {
        String message = Arrays.stream(errors)
                .map(SAXParseException::getMessage)
                .collect(Collectors.joining("; "))
                .trim();

        marshaller.marshal(
                Response.error("400", message),
                ((SoapMessage) messageContext.getResponse()).getSoapBody().getPayloadResult()
        );

        return false;
    }


    public record SchemaViolation(String message, int line, int column) {
    }
}
