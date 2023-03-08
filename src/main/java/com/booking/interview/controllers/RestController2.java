package com.booking.interview.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.interview.services.Service2;

@RestController
@RequestMapping("/api")
public class RestController2 {

    @Autowired
    Service2 service;

    @PostMapping("/fib")
    @Secured("ROLE_managers")
    public ResponseEntity<FibResponse> fib(@RequestBody FibRequest request) {

        try {
            return ResponseEntity.ok(new FibResponse(service.fib(request.num)));
        } catch (Service2.ServiceException e) {
            return ResponseEntity.status(e.getStatus()).build();
        }
    }

    public record FibResponse(Integer fib) {
    }

    public record FibRequest(Integer num) {
    }

}
