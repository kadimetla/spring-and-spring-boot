package com.kousenit.restclient.json;

import java.util.List;

public class LaunchLibraryRecords {
    // Root response
    public record ExpeditionResponse(
            int count,
            List<Expedition> results
    ) {}

    // Expedition with space station and crew
    public record Expedition(
            int id,
            String name,
            String start,
            String end,
            SpaceStation spacestation,
            List<CrewMember> crew
    ) {}

    // Space station basics
    public record SpaceStation(
            int id,
            String name,
            String orbit
    ) {}

    // Crew assignment (role + astronaut)
    public record CrewMember(
            Role role,
            Astronaut astronaut
    ) {}

    public record Role(
            String role
    ) {}

    // Astronaut essentials
    public record Astronaut(
            int id,
            String name,
            Agency agency,
            List<Nationality> nationality,
            String time_in_space,
            String bio
    ) {}

    public record Agency(
            String name,
            String abbrev
    ) {}

    public record Nationality(
            String name,
            String nationality_name
    ) {}

    // Flattened view of astronaut assignments
    public record AstronautAssignment(
            String astronautName,
            String role,
            String agency,
            String stationName
    ) {}
}
