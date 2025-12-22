package com.texnoera.orderssoapclient.controller;

import com.texnoera.orderssoapclient.client.OrderSoapClient;
import com.texnoera.orderssoapclient.ws.GetOrderRequest;
import com.texnoera.orderssoapclient.ws.GetOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrderSoapClient orderSoapClient;

    @GetMapping
    public GetOrderResponse getOrder(@RequestParam String orderId,
                                     @RequestParam String requestId) {
        GetOrderRequest getOrderRequest = new GetOrderRequest();
        getOrderRequest.setOrderId(orderId);
        getOrderRequest.setRequestId(requestId);
        return orderSoapClient.getOrder(getOrderRequest);
    }


}
