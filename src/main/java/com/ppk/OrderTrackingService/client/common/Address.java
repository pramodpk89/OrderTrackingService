package com.ppk.OrderTrackingService.client.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String addressLine1;
    private String state;
    private int postalCode;
    private String countryCode;

}