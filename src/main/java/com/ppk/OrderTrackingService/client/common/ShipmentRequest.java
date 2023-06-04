package com.ppk.OrderTrackingService.client.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentRequest {
    private String labelResponseOptions;
    private RequestedShipment requestedShipment;
    private AccountNumber accountNumber;
}
