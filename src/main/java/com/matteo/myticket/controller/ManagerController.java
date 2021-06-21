package com.matteo.myticket.controller;

import com.matteo.myticket.dto.AccountDetailsDTO;
import com.matteo.myticket.model.Business;
import com.matteo.myticket.model.Manager;
import com.matteo.myticket.model.User;
import com.matteo.myticket.security.util.CurrentUserService;
import com.matteo.myticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/manager")
public class ManagerController {

    @Autowired
    CurrentUserService currentUserService;

    @Autowired
    UserService userService;

    @GetMapping("/accountDetails")
    public ResponseEntity<AccountDetailsDTO> getAccountDetails(){
        Manager  user = (Manager) currentUserService.getCurrentUser();
        AccountDetailsDTO detailsDTO = new AccountDetailsDTO();

        detailsDTO.setUsername(user.getUsername());
        detailsDTO.setBusinessName(user.getBusiness().getName());
        detailsDTO.setFirstName(user.getFirstName());
        detailsDTO.setLastName(user.getLastname());

        return ResponseEntity.ok(detailsDTO);
    }

    @PostMapping("/accountDetails")
    public ResponseEntity<AccountDetailsDTO> updateAccountDetails(@RequestBody AccountDetailsDTO detailsDTO){
        Manager user = (Manager) currentUserService.getCurrentUser();

        user.getBusiness().setName(detailsDTO.getBusinessName());
        user.setFirstName(detailsDTO.getFirstName());
        user.setLastname(detailsDTO.getLastName());

        userService.add(user);

        return ResponseEntity.ok(detailsDTO);
    }
}
