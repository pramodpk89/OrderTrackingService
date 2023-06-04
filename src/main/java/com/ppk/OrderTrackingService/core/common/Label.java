package com.ppk.OrderTrackingService.core.common;

//Label.java
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Label {
    private String trackingNumber;
    private String trackingUrl;
}
