package com.matteo.myticket.dto;

import com.matteo.myticket.model.OrderTicket;
import lombok.Data;

import java.util.List;

@Data
public class DashboardDTO {

    private Double totalIncome;
    private Integer ticketsSold;
    private List<EventStats> orders;
}


