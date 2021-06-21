package com.matteo.myticket.controller;

import com.matteo.myticket.dto.BusinessInfoViewDTO;
import com.matteo.myticket.model.Business;
import com.matteo.myticket.model.Manager;
import com.matteo.myticket.service.EventService;
import com.matteo.myticket.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public/business")
public class BusinessController {

    @Autowired
    ManagerService managerService;

    @Autowired
    EventService eventService;

    @GetMapping("/event/{id}")
    public ResponseEntity<BusinessInfoViewDTO> getBusinessInfoByEvent(@PathVariable Integer id){
        return ResponseEntity.ok(eventService.businessInfoViewDTO(id));
    }
}
