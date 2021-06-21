package com.matteo.myticket.controller;

import com.matteo.myticket.dto.UserAccountDetailsDTO;
import com.matteo.myticket.model.User;
import com.matteo.myticket.security.util.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    CurrentUserService currentUserService;

    @GetMapping("/accountDetails")
    public ResponseEntity<UserAccountDetailsDTO> getAccountInfo(){

        User user = currentUserService.getCurrentUser();

        UserAccountDetailsDTO response = new UserAccountDetailsDTO();
        response.setUsername(user.getUsername());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastname());

        return ResponseEntity.ok(response);

    }
}
