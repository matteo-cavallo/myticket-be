package com.matteo.myticket.service;

import com.matteo.myticket.dto.DashboardDTO;
import com.matteo.myticket.dto.EventStats;
import com.matteo.myticket.model.Business;
import com.matteo.myticket.model.Event;
import com.matteo.myticket.model.OrderTicket;
import com.matteo.myticket.repo.DashboardRepo;
import com.matteo.myticket.security.util.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StatisticService {

    @Autowired
    CurrentUserService currentUserService;

    @Autowired
    DashboardRepo dashboardRepo;

    public DashboardDTO getDashboardView(){
        DashboardDTO dashboardDTO = new DashboardDTO();

        Business business = currentUserService.getCurrentBusiness();

        dashboardDTO.setTicketsSold(dashboardRepo.getSoldTicket(business.getId()));
        dashboardDTO.setTotalIncome(dashboardRepo.getTotalIncome(business.getId()));

        return dashboardDTO;
    }

}
