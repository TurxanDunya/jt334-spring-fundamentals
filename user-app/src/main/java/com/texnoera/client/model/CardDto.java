package com.texnoera.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private Long id;
    private String uid;
    private String pan;
    private String cardHolderName;

}
