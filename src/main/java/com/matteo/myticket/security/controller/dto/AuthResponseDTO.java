package com.matteo.myticket.security.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class AuthResponseDTO {

    private String username;
    private String token;
    private List<String> authorities;
}
