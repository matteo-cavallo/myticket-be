package com.matteo.myticket.controller;

import com.matteo.myticket.model.Business;
import com.matteo.myticket.model.Event;
import com.matteo.myticket.model.Manager;
import com.matteo.myticket.model.Ticket;
import com.matteo.myticket.security.util.CurrentUserService;
import com.matteo.myticket.service.EventService;
import com.matteo.myticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/manager/event")
public class EventCrudController {

    @Autowired
    EventService eventService;

    @Autowired
    CurrentUserService userService;

    @Autowired
    TicketService ticketService;


    @PostMapping
    @Transactional
    public ResponseEntity<Event> createEvent(@RequestBody Event event){
        Business currentBusiness = userService.getCurrentBusiness();
        event.setBusiness(currentBusiness);
        event.setTickets(new ArrayList<>());
        return ResponseEntity.ok(eventService.add(event));
    }

    @GetMapping
    public ResponseEntity<List<Event>> getEvents(){
        Business business = userService.getCurrentBusiness();
        return ResponseEntity.ok(eventService.findAllByBusiness(business.getId()));

    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEvent(@PathVariable Integer eventId){
        return ResponseEntity.ok(eventService.findById(eventId).orElseThrow(() -> new IllegalStateException("Not Found")));
    }


    @PostMapping("/{eventId}/ticket")
    public ResponseEntity<Ticket> createTicket(@PathVariable Integer eventId, @RequestBody Ticket ticket){
        Event event = eventService.findById(eventId).orElseThrow(() -> new IllegalStateException("Not found"));
        ticket.setEvent(event);
        ticketService.add(ticket);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/{eventId}/ticket")
    public ResponseEntity<List<Ticket>> getTicketsByEvent(@PathVariable Integer eventId){
        return ResponseEntity.ok(ticketService.findByEventId(eventId));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> deleteTicket(@PathVariable Integer eventId){
        eventService.deleteById(eventId);
        return ResponseEntity.ok().build();
    }

}
