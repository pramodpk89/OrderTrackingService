package com.ppk.OrderTrackingService.client.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    private String personName;
    private long phoneNumber;
    private String companyName;

}
