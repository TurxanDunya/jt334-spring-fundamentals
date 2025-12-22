package com.texnoera.client.feignerror;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.texnoera.client.feignerror.model.CardApiError;
import com.texnoera.error.exceptions.BadRequestException;
import com.texnoera.error.exceptions.CardNotFoundException;
import com.texnoera.error.exceptions.InternalErrorException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

@Slf4j
@Configuration
public class ClientErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    public ClientErrorDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        try (InputStream body = response.body().asInputStream()) {
            CardApiError responseBody = objectMapper.readValue(body, CardApiError.class);
            switch (response.status()) {
                case 404 -> {
                    return new CardNotFoundException(responseBody.getMessage());
                }
                case 400 -> {
                    return new BadRequestException(responseBody.getMessage());
                }
                default -> {
                    return new InternalErrorException(responseBody.getMessage());
                }
            }
        } catch (Exception ex) {
            log.error("Exception occurs while parsing client error response: {}", response, ex);
            return new InternalErrorException("Internal error happened while parsing client error model");
        }
    }

}
