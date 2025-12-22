package com.texnoera.bffmobile.dto;

import lombok.Data;

import javax.smartcardio.Card;
import java.util.Map;

@Data
public class CardResponse {

    private String uid;
    private String pan;
    private String cardHolderName;
    private Map<String, Card> cardsByNumber;

}
