package com.ppk.OrderTrackingService.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.ppk.OrderTrackingService.client.TrackingServiceRequest;
import com.ppk.OrderTrackingService.client.TrackingServiceResponse;
import com.ppk.OrderTrackingService.client.common.Address;
import com.ppk.OrderTrackingService.client.common.Contact;
import com.ppk.OrderTrackingService.client.common.RequestedShipment;
import com.ppk.OrderTrackingService.client.common.Shipper;
import com.ppk.OrderTrackingService.core.OrderRequest;
import com.ppk.OrderTrackingService.core.OrderResponse;
import com.ppk.OrderTrackingService.core.common.Attributes;
import com.ppk.OrderTrackingService.core.common.Data;
import com.ppk.OrderTrackingService.core.common.Label;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class OrderTrackingService {

    private final WebClient webClient;

    @Value("${oauth.oauthUrl}")
    private String oauthUrl;

    @Value("${oauth.clientId}")
    private String clientId;

    @Value("${oauth.clientSecret}")
    private String clientSecret;

    @Value("${oauth.grantType}")
    private String grantType;

    @Value("${ppk.service.carrierServiceUrl}")
    private String carrierServiceUrl;

    public OrderTrackingService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<OrderResponse> processOrderTrackingRequest(Mono<OrderRequest> request) {
        return request.flatMap(this::transformRequest)
                .flatMap(transformedRequest -> this.getAuthToken()
                        .flatMap(authToken -> this.callCarrierService(authToken, transformedRequest)))
                .map(this::prepareResponse);
    }

    private Mono<TrackingServiceRequest> transformRequest(OrderRequest request) {
        return Mono.fromCallable(() -> {
            TrackingServiceRequest trackingRequest = mapRequest();
            return trackingRequest;
        });
    }

    private Mono<String> getAuthToken() {
        // Use WebClient to make the request
        return this.webClient.post()
                .uri(oauthUrl)
                .bodyValue(Map.of(
                        "client_id", this.clientId,
                        "client_secret", this.clientSecret,
                        "grant_type", this.grantType))
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(jsonNode -> jsonNode.get("access_token").asText())
                .onErrorResume(e -> {
                    System.err.println("Failed to get auth token: " + e.getMessage());
                    return Mono.just("12345");
                });
    }

    private Mono<OrderResponse> callCarrierService(String authToken, TrackingServiceRequest transformedRequest) {
        return this.webClient.post()
                .uri(carrierServiceUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
                .body(Mono.just(transformedRequest), TrackingServiceRequest.class)
                .retrieve()
                .bodyToMono(TrackingServiceResponse.class)
                .map(this::transformResponseToDomain)
                .onErrorResume(e -> {
                    System.err.println("Failed to call carrier service: " + e.getMessage());
                    return Mono.empty();
                });
    }

    private OrderResponse transformResponseToDomain(TrackingServiceResponse response) {
        OrderResponse orderResponse=new OrderResponse();
        Data data=new Data();
        Attributes attributes=new Attributes();
        Label label=Label.builder().trackingNumber(response.getTrackingId()).trackingUrl(response.getTrackingUrl()).build();
        attributes.setLabel(label);
        data.setAttributes(attributes);
        orderResponse.setData(data);
        return orderResponse;
    }

    private OrderResponse prepareResponse(OrderResponse carrierResponse) {
        // TODO: Implement the logic to prepare the response here
        return carrierResponse;
    }


    private static TrackingServiceRequest mapRequest() {
        TrackingServiceRequest trackingRequest = TrackingServiceRequest.builder()
                .reqId("001")
                .labelResponseOptions("URL_ONLY")
                .requestedShipment(
                        RequestedShipment.builder()
                                .shipper(
                                        Shipper.builder()
                                                .contact(
                                                        Contact.builder()
                                                                .companyName("ShipperCompanyName")
                                                                .personName("SHIPPERNAME")
                                                                .phoneNumber(123456789)
                                                                .build()
                                                )
                                                .address(
                                                        Address.builder()
                                                                .addressLine1("addressLine1")
                                                                .state("STATE")
                                                                .postalCode(72601)
                                                                .countryCode("US")
                                                                .build()
                                                )
                                                .build()
                                )
                                .recipients(
                                        List.of(
                                                Shipper.builder()
                                                        .contact(
                                                                Contact.builder()
                                                                        .companyName("RecipientCompanyName")
                                                                        .personName("RECIPIENTNAME")
                                                                        .phoneNumber(1234567890)
                                                                        .build()
                                                        )
                                                        .address(
                                                                Address.builder()
                                                                        .addressLine1("RECIPIENTSTREETLINE1")
                                                                        .state("STATE")
                                                                        .postalCode(72601)
                                                                        .countryCode("US")
                                                                        .build()
                                                        )
                                                        .build()
                                        )
                                )
                                .shipDatestamp("2021-06-30")
                                .serviceType("serviceType")
                                .packagingType("YOUR_PACKAGING")
                                .build()
                )
                .build();
        return trackingRequest;
    }
}
