package com.matteo.myticket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private Date date;
    private Time time;

    @ManyToOne
    @JsonIgnore
    private Business business;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "event" )
    private List<Ticket> tickets;
}
