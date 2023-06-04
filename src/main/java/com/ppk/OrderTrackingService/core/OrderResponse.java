package com.ppk.OrderTrackingService.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private com.ppk.OrderTrackingService.core.common.Data data;
}
