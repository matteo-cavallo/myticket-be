package com.matteo.myticket.controller;

import com.matteo.myticket.dto.RegisterManagerRequestDTO;
import com.matteo.myticket.dto.RegisterUserDTO;
import com.matteo.myticket.model.Manager;
import com.matteo.myticket.model.User;
import com.matteo.myticket.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @PostMapping("/manager")
    public ResponseEntity<Manager> registerManager(@RequestBody RegisterManagerRequestDTO request){
        return ResponseEntity.ok(registerService.registerManager(request));

    }

    @PostMapping("/user")
    public ResponseEntity<User> registerUser(@RequestBody RegisterUserDTO request){
        return ResponseEntity.ok(registerService.registerUser(request));
    }
}
