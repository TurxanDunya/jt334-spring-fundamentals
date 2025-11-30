package com.texnoera.client.feignerror.model;

import lombok.Data;

@Data
public class CardApiError {

    private Integer code;
    private String message;

}
