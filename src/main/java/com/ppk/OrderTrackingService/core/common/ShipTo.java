package com.ppk.OrderTrackingService.core.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipTo {
    private Address address;
}