package com.musala.gateways.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Created by rbartolome on 22/07/19
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String sayHello() {
        return "Hello and Welcome to the Gateways application. You can create a new Gateway by making a POST request to /api/gateway endpoint.";
    }
}
