package com.matteo.myticket.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
public class Manager extends User{

    @OneToOne
    private Business business;
}
