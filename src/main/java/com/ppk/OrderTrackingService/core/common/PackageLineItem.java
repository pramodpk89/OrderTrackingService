package com.ppk.OrderTrackingService.core.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageLineItem {
    private int itemSequence;
    private String itemCode;
    private int quantity;
    private String uom;
    private String itemDescription;
    private String color;
    private String size;
    private String currency;
    private double unitPrice;
    private double retailPrice;
    private double listPrice;
}

