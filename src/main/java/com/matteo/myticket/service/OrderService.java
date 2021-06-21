package com.matteo.myticket.service;

import com.matteo.myticket.dto.NewOrderDTO;
import com.matteo.myticket.exception.ResourceNotFoundException;
import com.matteo.myticket.model.Order;
import com.matteo.myticket.model.OrderTicket;
import com.matteo.myticket.model.Ticket;
import com.matteo.myticket.model.User;
import com.matteo.myticket.repo.OrderRepo;
import com.matteo.myticket.repo.OrderTicketRepo;
import com.matteo.myticket.security.util.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrderTicketRepo orderTicketRepo;

    @Autowired
    CurrentUserService currentUserService;

    @Autowired
    TicketService ticketService;

    @Transactional
    public void newOrder(NewOrderDTO request){

        User user = currentUserService.getCurrentUser();

        Order order = new Order();
        order.setUser(user);
        orderRepo.save(order);

        List<Ticket> tickets = new ArrayList<>();
        tickets = request.getItems().stream().map(i -> {
            Ticket t = ticketService.findById(Integer.valueOf(i.getId())).orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
            return t;
        }).collect(Collectors.toList());

        List<OrderTicket> orderTickets = new ArrayList<>();
        orderTickets = tickets.stream().map(ticket -> {
           OrderTicket orderTicket = new OrderTicket();
           orderTicket.setOrder(order);
           orderTicket.setTicket(ticket);
           orderTicket.setTicketCode(UUID.randomUUID().toString());
           orderTicket.setCreatedDate(new Date());
           orderTicket.setValid(true);
           orderTicket.setPrice(ticket.getPrice());
           orderTicketRepo.save(orderTicket);
           return orderTicket;
        }).collect(Collectors.toList());

        System.out.println(orderTickets);

    }

    public List<Order> getAllUserOrder(){

        User user = currentUserService.getCurrentUser();
        return orderRepo.findAllByUser(user);
    }
}
