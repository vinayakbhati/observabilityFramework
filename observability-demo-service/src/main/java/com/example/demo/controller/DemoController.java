
package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/hello")
    public String sayHello() throws InterruptedException {
        Thread.sleep(200); // Simulate latency
        return "Hello from Observability Framework Demo!";
    }
}
