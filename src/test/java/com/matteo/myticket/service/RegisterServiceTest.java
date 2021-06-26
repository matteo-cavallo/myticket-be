package com.matteo.myticket.service;

import com.matteo.myticket.dto.RegisterManagerRequestDTO;
import com.matteo.myticket.model.Authority;
import com.matteo.myticket.model.Manager;
import com.matteo.myticket.repo.BusinessRepo;
import com.matteo.myticket.repo.ManagerRepo;
import com.matteo.myticket.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegisterServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    RegisterService registerService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    BusinessRepo businessRepo;

    private RegisterManagerRequestDTO getDriver(String username){
        String password = "passwordsupersecret";
        String businessName = "Nome Azienda";

        // Request Driver
        RegisterManagerRequestDTO requestDTO = new RegisterManagerRequestDTO();
        requestDTO.setUsername(username);
        requestDTO.setPassword(password);
        requestDTO.setBusinessName(businessName);
        return requestDTO;
    }

    @Test
    void itShouldRegisterCorrectlyAManager(){
        // given
        RegisterManagerRequestDTO driver = getDriver("username");

        // when
        Manager manager = registerService.registerManager(driver);

        // then
        assertNotEquals(driver.getPassword(), manager.getPassword());
        assertEquals(Authority.MANAGER, manager.getRole());
        assertNotEquals(null, manager.getBusiness());
    }

    @Test
    void itShouldNotSaveTwoManagerWithSameUsername() {

        RegisterManagerRequestDTO driver = getDriver("mariorossi");
        RegisterManagerRequestDTO driver1 = getDriver("mariorossi");

        boolean error = false;

        try {
            registerService.registerManager(driver);
            registerService.registerManager(driver1);
        } catch (Exception e) {
            error = true;
        } finally {
            assertTrue(error);
        }
    }
}
