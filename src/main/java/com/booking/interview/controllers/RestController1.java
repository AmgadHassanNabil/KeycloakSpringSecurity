package com.booking.interview.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.interview.services.Service1;

@RestController
@RequestMapping("/api")
public class RestController1 {

    @Autowired
    Service1 service;

    @PostMapping("/add")
    @Secured({"ROLE_EMPLOYEE"})
    public ResponseEntity<AddResponse> add(@RequestBody AddRequest request) {

        return ResponseEntity.ok(new AddResponse(service.add(request.a, request.b)));
    }

    public record AddResponse(
            Integer sum) {

    }

    public record AddRequest(
            Integer a,
            Integer b) {

    }

}
