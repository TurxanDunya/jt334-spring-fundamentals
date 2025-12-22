package com.texnoera.ordersoapservice.controller;

import com.texnoera.ordersoapservice.GetOrderRequest;
import com.texnoera.ordersoapservice.GetOrderResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class OrderEndpoint {

    private static final String NAMESPACE_URI = "http://localhost:8080/orders";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetOrderRequest")
    @ResponsePayload
    public GetOrderResponse getOrder(@RequestPayload GetOrderRequest request) {
        GetOrderResponse response = new GetOrderResponse();
        response.setStatus("Order " + request.getOrderId() + " is processed!");
        response.setName("Just for fun!");
        return response;
    }

}
