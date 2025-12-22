package com.texnoera.orderssoapclient.config;

import com.texnoera.orderssoapclient.client.OrderSoapClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class OrdersSoapConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.texnoera.orderssoapclient.ws");
        return marshaller;
    }

    @Bean
    public OrderSoapClient orderSoapClient(Jaxb2Marshaller marshaller) {
        OrderSoapClient client = new OrderSoapClient();
        client.setDefaultUri("http://localhost:8080/ws/orders");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
