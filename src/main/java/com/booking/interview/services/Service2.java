package com.booking.interview.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.booking.interview.controllers.RestController1.AddRequest;
import com.booking.interview.controllers.RestController1.AddResponse;

@Service
public class Service2 {

    @Autowired
    RestTemplate restTemplate;

    public Integer fib(int n) throws ServiceException {

        if (n <= 2)
            return 1;
        AddRequest requestBody = new AddRequest(fib(n - 1), fib(n - 2));
        ResponseEntity<AddResponse> response = restTemplate.postForEntity("http://localhost:8080/api/add",
            requestBody,
            AddResponse.class);

        if(response.getStatusCode() != HttpStatus.OK)
            throw new ServiceException(response.getStatusCode());

        return response.getBody().sum();
    }

    public static class ServiceException extends Exception {
        HttpStatus status;

        public ServiceException(HttpStatus status) {
            this.status = status;
        }

        public HttpStatus getStatus() {
            return status;
        }

    }
}
