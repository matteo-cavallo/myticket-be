package com.matteo.myticket.controller;

import com.matteo.myticket.exception.ResourceNotFoundException;
import com.matteo.myticket.model.Ticket;
import com.matteo.myticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public/tickets")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Integer id){
        return ResponseEntity.ok(ticketService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ticket not FOund")));
    }
}
