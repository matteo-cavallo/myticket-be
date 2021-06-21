package com.matteo.myticket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Ciao");
    }


    @GetMapping ("/auth")
    public ResponseEntity<String> testAuth(){
        return ResponseEntity.ok("Perfetto");
    }

    @GetMapping ("/role")
    public ResponseEntity<String> testRole(){
        return ResponseEntity.ok("Perfetto");
    }

}
