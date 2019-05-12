package com.reactive.spring.springreactive;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxTestExample {

    @Test
    public void flux_iterate() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Spring Reactive");

        stringFlux
                .subscribe(System.out::println);
    }

    @Test
    public void flux_iterate_with_error() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Spring Reactive")
                .concatWith(Flux.error(new RuntimeException("Runtime Exception Occurs")));

        stringFlux
                .subscribe(
                        System.out::println,
                        e -> System.out.println(e)
                );
    }

    @Test
    public void flux_interate_error_log() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot")
                .concatWith(Flux.error(new RuntimeException("Exception Occurs")))
                .concatWith(Flux.just("After Exception"))
                .log();

        stringFlux
                .subscribe(
                        System.out::println,
                        e -> System.out.println(e),
                        () -> System.out.println("Completed")
                );
    }

    @Test
    public void flux_element_without_error() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Spring Reactive");

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Spring Reactive")
                .verifyComplete();
    }

    @Test
    public void flux_element_with_error() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Spring Reactive")
                .concatWith(Flux.error(new RuntimeException("Exception")));


        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Spring Reactive")
//                .expectErrorMessage("Exception")
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void flux_element_count() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Spring Reactive")
                .concatWith(Flux.error(new RuntimeException("Exception")));

        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .expectError(RuntimeException.class)
                .verify();
    }
}
