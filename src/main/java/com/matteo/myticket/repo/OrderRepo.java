package com.matteo.myticket.repo;

import com.matteo.myticket.model.Order;
import com.matteo.myticket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Integer> {

    List<Order> findAllByUser(User user);
}
