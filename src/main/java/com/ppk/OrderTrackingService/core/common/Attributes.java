package com.ppk.OrderTrackingService.core.common;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attributes {
    private ShipFrom shipFrom;
    private ShipTo shipTo;
    private ReturnTo returnTo;
    private List<PackageDetail> packageDetail;
    private Label label;
}
