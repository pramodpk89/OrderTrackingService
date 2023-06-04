package com.ppk.OrderTrackingService.app;

import com.ppk.OrderTrackingService.core.OrderRequest;
import com.ppk.OrderTrackingService.core.OrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderTrackingController {

    private final OrderTrackingService orderTrackingService;
    private static final Logger logger = LoggerFactory.getLogger(OrderTrackingController.class);

    @Autowired
    public OrderTrackingController(OrderTrackingService orderTrackingService) {
        this.orderTrackingService = orderTrackingService;
    }

    @PostMapping("/tracking")
    public Mono<OrderResponse> getOrderTrackingDetails(@RequestBody Mono<OrderRequest> request) {
        logger.info("Received order tracking request :");
        return orderTrackingService.processOrderTrackingRequest(request)
                .doOnSuccess(response -> logger.info("Order tracking request processed successfully"))
                .doOnError(throwable -> logger.error("Error processing order tracking request: {}", throwable.getMessage()));
    }
}
