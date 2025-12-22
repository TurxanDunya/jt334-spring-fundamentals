package com.texnoera.cardapi.controller;

import com.texnoera.cardapi.error.exceptions.CardNotFoundException;
import com.texnoera.cardapi.model.dto.CardDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/cards")
public class CardController {

    @GetMapping("/{id}")
    public CardDto getCardById(@PathVariable Long id) throws InterruptedException {
        log.info("Getting card by id: {}", id);

        if (id > 1000) {
            throw new CardNotFoundException("Card not found with id: " + id);
        }

        return CardDto.builder()
                .id(id)
                .pan("4169738834567890")
                .uid(UUID.randomUUID().toString())
                .cardHolderName("Turkhan Dunyamaliyev")
                .build();
    }

    @PostMapping
    public void createCard(@RequestBody CardDto cardDto) {
        log.info("Created card: {}", cardDto);
    }

    @PutMapping
    public void updateCard(@RequestBody CardDto cardDto) {
        log.info("Updated card: {}", cardDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id) {
        log.info("Deleted card: {}", id);
    }

}
