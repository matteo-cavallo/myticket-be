package com.matteo.myticket.controller;

import com.matteo.myticket.dto.DashboardDTO;
import com.matteo.myticket.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/manager/dashboard")
public class DashboardController {

    @Autowired
    StatisticService statisticService;

    @GetMapping
    public ResponseEntity<DashboardDTO> getDashboardView(){
        return ResponseEntity.ok(statisticService.getDashboardView());
    }
}
