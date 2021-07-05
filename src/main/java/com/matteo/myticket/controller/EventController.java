package com.matteo.myticket.controller;

import com.matteo.myticket.exception.ResourceNotFoundException;
import com.matteo.myticket.model.Event;
import com.matteo.myticket.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/public/events")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/upcoming")
    public ResponseEntity<List<Event>> upcomingEvents(){
        return ResponseEntity.ok(eventService.findUpComing());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Event>> allEvents(){
        return ResponseEntity.ok(eventService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Integer id){
        return ResponseEntity.ok(eventService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found Event with id: " + id)));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Event>> searchEvents(@PathVariable String name){
        System.out.println("Searching " + name);
        return ResponseEntity.ok(eventService.searchByTitle(name));
    }
}
