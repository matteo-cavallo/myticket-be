package com.matteo.myticket.controller;

import com.matteo.myticket.model.Event;
import com.matteo.myticket.model.Ticket;
import com.matteo.myticket.service.EventService;
import com.matteo.myticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/manager/ticket/")
public class TicketCrudController {

    @Autowired
    EventService eventService;

    @Autowired
    TicketService ticketService;

    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Integer ticketId){
        return ResponseEntity.ok(ticketService.findById(ticketId).orElseThrow(() -> new IllegalStateException("Not Found")));
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<?> deleteTicket(@PathVariable Integer ticketId){
        ticketService.deleteById(ticketId);
        return ResponseEntity.ok().build();
    }
}
