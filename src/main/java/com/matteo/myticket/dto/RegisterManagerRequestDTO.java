package com.matteo.myticket.dto;

import lombok.Data;

@Data
public class RegisterManagerRequestDTO {

    private String username;
    private String password;
    private String businessName;
}
