package com.ppk.OrderTrackingService.client.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestedShipment {
    private Shipper shipper;
    private List<Shipper> recipients;
    private String shipDatestamp;
    private String serviceType;
    private String packagingType;
    private String pickupType;
}
