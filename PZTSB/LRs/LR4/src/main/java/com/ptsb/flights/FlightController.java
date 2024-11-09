package com.ptsb.flights;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @GetMapping("/")
    public List<Flight> getFlights() {
        return FlightService.getFlights();
    }

    @GetMapping("/{id}/")
    public Flight getFlight(
        @PathVariable UUID id
    ){
        return FlightService.getFlightById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Flight> createFlight(
        @RequestBody Flight flight
    ){
        return new ResponseEntity<>(
            FlightService.createFlight(flight),
            HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<Void> deleteFlight(
        @PathVariable UUID id
    ){
        FlightService.deleteFlight(id);
        return new ResponseEntity<>(
            HttpStatus.NO_CONTENT
        );
    }
}
