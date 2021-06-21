package com.matteo.myticket.service;

import com.matteo.myticket.dto.RegisterManagerRequestDTO;
import com.matteo.myticket.dto.RegisterUserDTO;
import com.matteo.myticket.model.Authority;
import com.matteo.myticket.model.Business;
import com.matteo.myticket.model.Manager;
import com.matteo.myticket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
public class RegisterService {

    @Autowired
    UserService userService;

    @Autowired
    BusinessService businessService;

    @Transactional
    public Manager registerManager(RegisterManagerRequestDTO request){
        Manager manager = new Manager();
        Business business = new Business();

        business.setName(request.getBusinessName());
        businessService.add(business);

        manager.setUsername(request.getUsername());
        manager.setPassword(request.getPassword());
        manager.setBusiness(business);
        manager.setAuthorities(Arrays.asList(Authority.MANAGER));
        userService.add(manager);

        return manager;
    }


    @Transactional
    public User registerUser(RegisterUserDTO request){
        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setAuthorities(Arrays.asList(Authority.USER));

        userService.add(user);
        return user;
    }
}
