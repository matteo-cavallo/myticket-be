package com.matteo.myticket.security.util;

import com.matteo.myticket.model.Business;
import com.matteo.myticket.model.Manager;
import com.matteo.myticket.model.User;
import com.matteo.myticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    @Autowired
    UserService userService;

    public User getCurrentUser(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userService.findByUsername(username);
    }

    public Business getCurrentBusiness(){
        Business business = ((Manager) getCurrentUser()).getBusiness();
        return business;
    }
}
