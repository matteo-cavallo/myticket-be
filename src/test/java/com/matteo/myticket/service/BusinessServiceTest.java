package com.matteo.myticket.service;

import com.matteo.myticket.model.Business;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BusinessServiceTest {

    @Autowired
    BusinessService businessService;


    @Test
    void shouldSaveBusinessCorrectly() {
        // given
        String name = "Azienda";
        String description = "Descrizione Azienda";

        // Business Driver
        Business business = new Business();
        business.setName(name);
        business.setDescription(description);

        // when
        Business savedBusiness = businessService.add(business);

        // then
        assertNotEquals(null, savedBusiness.getId());
        assertEquals(name, savedBusiness.getName());
        assertEquals(description, savedBusiness.getDescription());
    }
}
