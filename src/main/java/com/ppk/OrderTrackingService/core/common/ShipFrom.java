package com.ppk.OrderTrackingService.core.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipFrom {
    private Address address;
    private String accountNumber;
    private String locationId;
}