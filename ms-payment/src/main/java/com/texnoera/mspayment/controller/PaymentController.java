package com.texnoera.mspayment.controller;

import com.texnoera.mspayment.model.CreateOrderRequestDto;
import com.texnoera.mspayment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/order")
    public void createOrder(CreateOrderRequestDto requestDto) {
        paymentService.createOrder(requestDto);
    }

}
