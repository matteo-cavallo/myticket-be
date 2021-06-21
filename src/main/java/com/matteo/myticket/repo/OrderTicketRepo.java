package com.matteo.myticket.repo;

import com.matteo.myticket.model.OrderTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTicketRepo extends JpaRepository<OrderTicket, Integer> {
}
