package com.matteo.myticket.repo;

import com.matteo.myticket.dto.EventStats;
import com.matteo.myticket.model.Event;
import com.matteo.myticket.model.OrderTicket;
import com.matteo.myticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.SqlResultSetMapping;
import java.util.List;

@Repository
public interface DashboardRepo extends JpaRepository<Ticket, Integer>{

    @Query("select count(t) FROM OrderTicket t WHERE t.ticket.event.business.id=:businessID")
    public Integer getSoldTicket(@Param("businessID") Integer id);

    @Query("select sum(t.price) FROM OrderTicket t WHERE t.ticket.event.business.id=:businessID")
    public Double getTotalIncome(@Param("businessID") Integer id);

}
