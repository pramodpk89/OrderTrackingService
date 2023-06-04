package com.ppk.OrderTrackingService.core.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String state;
    private String provinceCode;
    private String country;
    private String zipcode;
}
