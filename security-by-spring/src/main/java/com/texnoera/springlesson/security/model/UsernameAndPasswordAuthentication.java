package com.texnoera.springlesson.security.model;

import lombok.Data;

@Data
public class UsernameAndPasswordAuthentication {

    private String username;
    private String password;

}
