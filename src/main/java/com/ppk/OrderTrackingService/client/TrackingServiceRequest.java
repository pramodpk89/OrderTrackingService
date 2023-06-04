package com.ppk.OrderTrackingService.client;

import com.ppk.OrderTrackingService.client.common.AccountNumber;
import com.ppk.OrderTrackingService.client.common.RequestedShipment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrackingServiceRequest {
    String reqId;
    String labelResponseOptions;
    RequestedShipment requestedShipment;
    AccountNumber accountNumber;

}
