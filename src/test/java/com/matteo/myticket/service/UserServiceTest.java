package com.matteo.myticket.service;

import com.matteo.myticket.model.Manager;
import com.matteo.myticket.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;


    @Test
    void itShouldSaveManagerCorrectly() {
        // Given
        String password = "passwordsupersecret";

        // Manager Driver
        Manager manager = new Manager();
        manager.setUsername("manager 1");
        manager.setFirstName("matteo");
        manager.setLastname("cavallo");
        manager.setPassword(password);

        // When
        Manager savedManager = (Manager) userService.add(manager);

        // Then
        assertNotEquals(null, savedManager);
        assertNotEquals(null, savedManager.getId());
        assertNotEquals(password, savedManager.getPassword());
    }
}
