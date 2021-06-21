package com.matteo.myticket.repo;

import com.matteo.myticket.model.Business;
import com.matteo.myticket.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepo extends JpaRepository<Event, Integer> {

    List<Event> findAllByBusiness_Id(Integer business);

    List<Event> findAllByOrderByDateAsc();

    List<Event> findTop5ByOrderByDateAsc();

}
