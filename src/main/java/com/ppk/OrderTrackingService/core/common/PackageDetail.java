package com.ppk.OrderTrackingService.core.common;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageDetail {
    private List<PackageLineItem> packageLineItems;
}
