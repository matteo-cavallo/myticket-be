package com.matteo.myticket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class OrderTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String ticketCode;
    private Boolean valid;
    private Double price;

    @CreationTimestamp
    private Date createdDate;

    @ManyToOne
    private Ticket ticket;

    @ManyToOne
    @JsonIgnore
    private Order order;


    @Override
    public String toString() {
        return "OrderTicket{" +
                "id=" + id +
                ", ticketCode='" + ticketCode + '\'' +
                ", valid=" + valid +
                ", createdDate=" + createdDate +
                ", ticket=" + ticket.getId() +
                ", order=" + order.getId() +
                '}';
    }
}
