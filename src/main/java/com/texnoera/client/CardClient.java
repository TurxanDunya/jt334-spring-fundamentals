package com.texnoera.client;

import com.texnoera.client.model.CardDto;
import com.texnoera.error.exceptions.CardNotFoundException;
import com.texnoera.error.exceptions.InternalErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class CardClient {

    private static final String CARD_ROOT_URL = "http://localhost:8080/api/v1/cards/";

    private final RestTemplate restTemplate;

    public CardDto getCardById(Long id) {
        String url = CARD_ROOT_URL + id;

        ResponseEntity<CardDto> responseEntity = null;
        try {
            responseEntity = restTemplate.getForEntity(url, CardDto.class);
        } catch (Exception e) {
            handleExceptions(e);
        }

        return Objects.nonNull(responseEntity)
                ? responseEntity.getBody() : null;
    }

    public void createCard(CardDto cardDto) {
        String url = CARD_ROOT_URL;

        try {
            restTemplate.postForEntity(url, cardDto, String.class);
        } catch (Exception e) {
            handleExceptions(e);
        }
    }

    public void handleExceptions(Exception ex) {
        if (ex instanceof HttpClientErrorException) {
            throw new CardNotFoundException(ex.getMessage());
        }

        if (ex instanceof HttpServerErrorException) {
            throw new InternalErrorException("Server error occurred");
        }
    }

}
