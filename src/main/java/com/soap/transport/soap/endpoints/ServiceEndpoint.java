package com.soap.transport.soap.endpoints;

import com.soap.app.ServiceRecordFactory;
import com.soap.app.ValidationError;
import com.soap.contracts.incoming.CreateServiceRequest;
import com.soap.contracts.incoming.DeleteServiceRequest;
import com.soap.contracts.incoming.ReadServiceRequest;
import com.soap.contracts.incoming.UpdateServiceRequest;
import com.soap.contracts.outgoing.ReadServiceResponse;
import com.soap.contracts.outgoing.Response;
import com.soap.infra.ServiceMemoryStore;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ServiceEndpoint {
    // TODO: can be moved to properties
    private static final String NAMESPACE = "test";

    private final ServiceMemoryStore store;
    private final ServiceRecordFactory factory;

    @PreAuthorize("hasRole('WRITE')")
    @PayloadRoot(namespace = NAMESPACE, localPart = "Create")
    @ResponsePayload
    public Response create(@RequestPayload CreateServiceRequest request) {
        return factory.fromRequest(request).fold(
                fail -> {
                    // The specification defines a single error per response, so decision here to return the first validation error
                    var first = fail.errors().getFirst();
                    return Response.error(first.getCode(), first.getMessage());
                },
                ok -> store.saveIfAbsent(ok.record())
                        ? Response.success("Service activated successfully")
                        : factory.toErrorResponse(ValidationError.SERVICE_ALREADY_EXISTS)
        );
    }

    @PreAuthorize("hasRole('READ')")
    @PayloadRoot(namespace = NAMESPACE, localPart = "Read")
    @ResponsePayload
    public ReadServiceResponse read(@RequestPayload ReadServiceRequest request) {
        var record = store.findById(request.getServiceId());
        return record.isPresent()
                ? factory.toResponse(record.get())
                : factory.toResponse(factory.toErrorResponse(ValidationError.SERVICE_NOT_FOUND));
    }

    @PreAuthorize("hasRole('WRITE')")
    @PayloadRoot(namespace = NAMESPACE, localPart = "Update")
    @ResponsePayload
    public Response update(@RequestPayload UpdateServiceRequest request) {
        return factory.fromRequest(request).fold(
                fail -> {
                    var first = fail.errors().getFirst();
                    return Response.error(first.getCode(), first.getMessage());
                },
                ok -> store.updateIfExists(ok.record())
                        ? Response.success("Service updated successfully")
                        : factory.toErrorResponse(ValidationError.SERVICE_NOT_FOUND)
        );
    }

    @PreAuthorize("hasRole('WRITE')")
    @PayloadRoot(namespace = NAMESPACE, localPart = "Delete")
    @ResponsePayload
    public Response delete(@RequestPayload DeleteServiceRequest request) {
        return store.deleteById(request.getServiceId())
                ? Response.success("Service deleted successfully")
                : factory.toErrorResponse(ValidationError.SERVICE_NOT_FOUND);
    }
}
