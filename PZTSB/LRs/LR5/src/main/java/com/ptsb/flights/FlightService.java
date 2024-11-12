package com.ptsb.flights;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FlightService {
    private static final ArrayList<Flight> flightsList = new ArrayList<>(
            Arrays.asList(
                new Flight(
                    UUID.fromString("20c31cfb-d47b-4e74-8750-15f439b0eb17"),
                    "New Jersy",
                    "Miami",
                    "2024-01-01T10:00:00+00:00",
                    "2024-01-01T16:00:00+00:00",
                    123,
                    "A123B"
                )
            )
    );

    public static List<Flight> getFlights(){
        return flightsList;
    }

    public static Flight getFlightById(UUID id) throws ResponseStatusException {
        var item = FlightService.flightsList.stream().filter(
                flight -> flight.getId().equals(id)
        ).findFirst();
        if (item.isEmpty()) throw new ResponseStatusException(
                HttpStatus.NOT_FOUND
        );
        return item.get();
    }

    public static Flight createFlight(Flight flight){
        FlightService.flightsList.add(flight);
        return flight;
    }

    public static void deleteFlight(UUID id){
        try {
            Flight flight = getFlightById(id);
            FlightService.flightsList.remove(flight);
        }
        catch (ResponseStatusException ignored){}
    }
}
