package com.kousenit.restclient.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class LaunchLibraryServiceTest {
    @Autowired
    private LaunchLibraryService service;

    @Test
    void astronaut_aboard_station() {
        assertNotNull(service);
        service.getExpeditions()
                .forEach(expedition -> {
                    String stationName = expedition.spacestation().name();
                    expedition.crew()
                            .forEach(member ->
                                    System.out.printf("%s (%s) aboard %s%n",
                                        member.astronaut().name(),
                                        member.role().role(),
                                        stationName));
                });
    }

    record AstronautAssignment(String astronautName, String role, String agency, String stationName) {}

    @Test
    void flatten_to_astronaut_assignments() {
        service.getExpeditions().stream()
                .flatMap(expedition -> expedition.crew().stream()
                        .map(member -> new AstronautAssignment(
                                member.astronaut().name(),
                                member.role().role(),
                                member.astronaut().agency().abbrev(),
                                expedition.spacestation().name()
                        )))
                .forEach(System.out::println);
    }

    @Test
    void group_by_space_station() {
        service.getExpeditions().stream()
                .collect(Collectors.groupingBy(
                        exp -> exp.spacestation().name(),
                        Collectors.summingLong(exp -> exp.crew().size())
                ))
                .forEach((station, crew) ->
                        System.out.printf("There are %d astronauts aboard the %s%n", crew, station));
    }

}