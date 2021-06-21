package com.matteo.myticket.security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matteo.myticket.model.User;
import com.matteo.myticket.security.controller.dto.AuthResponseDTO;
import com.matteo.myticket.security.controller.dto.LoginDTO;
import com.matteo.myticket.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.stream.Collectors;

@Controller
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody LoginDTO request){

        try{

            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // Getting Principal
            User user = (User) authenticate.getPrincipal();

            // Generating Token
            String token = jwtProvider.generateToken(user);

            // Mapping the response
            AuthResponseDTO response = new AuthResponseDTO();
            response.setUsername(user.getUsername());
            response.setToken(token);
            response.setAuthorities(user.getAuthorities().stream().map(a->a.getAuthority()).collect(Collectors.toList()));

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            token
                    )
                    .body(response);

        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
