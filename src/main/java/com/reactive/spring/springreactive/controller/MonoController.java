package com.reactive.spring.springreactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MonoController {

    @GetMapping(value = "/mono")
    public Mono<Integer> monoOfInteger() {

        return Mono.just(1).log();
    }
}
