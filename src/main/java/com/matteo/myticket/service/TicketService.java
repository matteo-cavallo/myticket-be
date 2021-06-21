package com.matteo.myticket.service;

import com.matteo.myticket.model.Ticket;
import com.matteo.myticket.repo.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService implements CRUDService<Ticket>{

    @Autowired
    TicketRepo ticketRepo;

    @Override
    public Ticket add(Ticket ticket) {
        return ticketRepo.save(ticket);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepo.findAll();
    }

    @Override
    public Optional<Ticket> findById(Integer id) {
        return ticketRepo.findById(id);
    }

    public List<Ticket> findByEventId(Integer id){
        return ticketRepo.findAllByEvent_Id(id);
    }

    public void deleteById(Integer id){
        ticketRepo.deleteById(id);
    }
}
