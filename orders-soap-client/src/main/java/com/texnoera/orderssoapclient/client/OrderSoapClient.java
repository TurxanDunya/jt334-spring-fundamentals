package com.texnoera.orderssoapclient.client;

import com.texnoera.orderssoapclient.ws.GetOrderRequest;
import com.texnoera.orderssoapclient.ws.GetOrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Slf4j
public class OrderSoapClient extends WebServiceGatewaySupport {

    public GetOrderResponse getOrder(GetOrderRequest getOrderRequest) {
        log.info("Requesting orders for: {}", getOrderRequest.getOrderId());

        return (GetOrderResponse) getWebServiceTemplate()
                .marshalSendAndReceive(getDefaultUri(), getOrderRequest);
    }

}
