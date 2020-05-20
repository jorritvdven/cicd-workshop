package com.jcore.cicd.helloworld;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello/{name}")
    public ResponseEntity<HelloReponse> sayHello(@PathVariable("name") String name) {
        return ResponseEntity.ok(
                HelloReponse.builder()
                        .message("Hello " + name + "! How are you?")
                        .build()
        );
    }
}
