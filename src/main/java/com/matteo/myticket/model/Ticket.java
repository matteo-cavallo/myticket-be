package com.matteo.myticket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private Double price;

    @ManyToOne
    @JsonIgnore
    private Event event;


    @OneToMany(mappedBy = "ticket")
    @JsonIgnore
    private List<OrderTicket> orderTickets;


    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
