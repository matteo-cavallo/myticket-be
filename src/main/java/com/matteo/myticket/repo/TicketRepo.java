package com.matteo.myticket.repo;

import com.matteo.myticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket,Integer> {

    List<Ticket> findAllByEvent_Id(Integer id);
}
