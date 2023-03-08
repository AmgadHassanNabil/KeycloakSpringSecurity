package com.booking.interview.controllers;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.booking.interview.controllers.RestController2.FibRequest;
import com.booking.interview.services.Service2;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RestController2Test {

    @Mock
    Service2 service;

    @InjectMocks
    RestController1 subject;

    MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testFib() throws Exception {

        FibRequest request = new FibRequest(6);

        when(this.service.fib(anyInt())).thenReturn(4);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(post("/api/fib").headers(headers)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print()).andExpect(jsonPath("$.fib").value(4))
                .andExpect(status().isOk());

    }
}
