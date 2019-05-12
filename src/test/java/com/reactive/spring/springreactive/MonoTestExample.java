package com.reactive.spring.springreactive;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTestExample {

    @Test
    public void mono_element_success() {

        Mono<String> stringMono = Mono.just("Spring");

        StepVerifier.create(stringMono)
                .expectNext("Spring")
                .expectComplete();
    }

    @Test
    public void mono_element_error() {

        Mono<String> stringMono = Mono.error(new RuntimeException("Runtime Exception"));

        StepVerifier.create(stringMono)
                .expectError(RuntimeException.class)
                .verify();

    }
}
