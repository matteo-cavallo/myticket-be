package com.matteo.myticket.controller;

import com.matteo.myticket.dto.NewOrderDTO;
import com.matteo.myticket.model.Order;
import com.matteo.myticket.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/newOrder")
    public ResponseEntity<?> createNewOrder(@RequestBody NewOrderDTO request){

        orderService.newOrder(request);
        return ResponseEntity.ok(request);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllUserOrder (){
        return ResponseEntity.ok(orderService.getAllUserOrder());
    }
}
