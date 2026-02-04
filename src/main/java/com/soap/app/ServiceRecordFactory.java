package com.soap.app;

import com.soap.contracts.ServiceDetails;
import com.soap.contracts.incoming.ServiceRequest;
import com.soap.contracts.outgoing.ReadServiceResponse;
import com.soap.contracts.outgoing.ServiceResponse;
import com.soap.contracts.outgoing.Response;
import com.soap.domain.Address;
import com.soap.domain.CustomerDetails;
import com.soap.domain.ServiceRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class ServiceRecordFactory {
    private static final Pattern CONTACT_PATTERN = Pattern.compile("^\\+\\d{11,15}$");
    private static final String VIP_CUSTOMER_ID = "123456789";

    public TransformResult fromRequest(ServiceRequest request) {
        if (!CONTACT_PATTERN.matcher(request.getCustomerDetails().getContactNumber()).matches()) {
            return new TransformResult.Fail(List.of(ValidationError.INVALID_CONTACT_NUMBER));
        }

        Boolean isVipCustomer = VIP_CUSTOMER_ID.equals(request.getCustomerId()) ? true : null;

        ServiceDetails sd = request.getServiceDetails();
        String specialOffer = ("5G".equals(sd.getPlanType()) && sd.getDataLimit() == null) ? "ExtraData" : null;

        String country = request.getCustomerDetails().getAddress().getCountry();
        Boolean isRoamingEnabled = "Sweden".equalsIgnoreCase(country) ? sd.getRoamingEnabled() : null;

        return new TransformResult.Ok(ServiceRecord.builder()
                .serviceId(request.getServiceId())
                .serviceType(request.getServiceType())
                .customerId(request.getCustomerId())
                .subscriptionId(request.getSubscriptionId())
                .vipCustomer(isVipCustomer)
                .serviceDetails(new com.soap.domain.ServiceDetails(
                        sd.getPlanType(),
                        sd.getDataLimit(),
                        isRoamingEnabled,
                        copyList(sd.getAdditionalServices()),
                        specialOffer
                ))
                .customerDetails(new CustomerDetails(
                        request.getCustomerDetails().getName(),
                        new Address(
                                request.getCustomerDetails().getAddress().getStreet(),
                                request.getCustomerDetails().getAddress().getCity(),
                                request.getCustomerDetails().getAddress().getPostalCode(),
                                request.getCustomerDetails().getAddress().getCountry()
                        ),
                        request.getCustomerDetails().getContactNumber()
                ))
                .build());
    }

    public ReadServiceResponse toResponse(ServiceRecord record) {
        return ReadServiceResponse.success(new ServiceResponse(
                record.getServiceId(),
                record.getServiceType(),
                record.getCustomerId(),
                record.getSubscriptionId(),
                record.getVipCustomer(),
                new ServiceResponse.ServiceDetails(
                        record.getServiceDetails().getPlanType(),
                        record.getServiceDetails().getDataLimit(),
                        record.getServiceDetails().getRoamingEnabled(),
                        copyList(record.getServiceDetails().getAdditionalServices()),
                        record.getServiceDetails().getSpecialOffer()
                ),
                new ServiceResponse.CustomerDetails(
                        record.getCustomerDetails().getName(),
                        new ServiceResponse.CustomerDetails.Address(
                                record.getCustomerDetails().getAddress().getStreet(),
                                record.getCustomerDetails().getAddress().getCity(),
                                record.getCustomerDetails().getAddress().getPostalCode(),
                                record.getCustomerDetails().getAddress().getCountry()
                        ),
                        record.getCustomerDetails().getContactNumber()
                )
        ));
    }

    public Response toErrorResponse(ValidationError error) {
        return Response.error(error.getCode(), error.getMessage());
    }

    public ReadServiceResponse toResponse(Response response) {
        return ReadServiceResponse.error(response.getErrorCode(), response.getErrorMessage());
    }

    private static <T> List<T> copyList(List<T> in) {
        return (in == null) ? List.of() : new ArrayList<>(in);
    }
}
