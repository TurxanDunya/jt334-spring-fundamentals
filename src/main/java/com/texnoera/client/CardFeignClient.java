package com.texnoera.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.texnoera.client.feignerror.ClientErrorDecoder;
import com.texnoera.client.model.CardDto;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "card-api",
        path = "/api/v1/cards",
        url = "${client.card.url}",
        configuration = CardFeignClient.FeignConfiguration.class)
public interface CardFeignClient {

    @GetMapping("/{id}")
    CardDto getCardById(@PathVariable Long id);

    class FeignConfiguration {

        @Bean
        public ErrorDecoder errorDecoder(ObjectMapper objectMapper) {
            return new ClientErrorDecoder(objectMapper);
        }

    }

}
