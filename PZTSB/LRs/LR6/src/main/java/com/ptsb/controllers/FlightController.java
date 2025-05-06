package com.ptsb.controllers;

import com.ptsb.dtos.CreateFlight;
import com.ptsb.errors.InvalidRequestException;
import com.ptsb.models.Flight;
import com.ptsb.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
    @Autowired
    FlightService flightService;

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<Flight> getFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{flightNumber}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<Flight> getFlightsByNumber(@PathVariable String flightNumber) {
        return flightService.getFlightsByFlightNumber(flightNumber);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Flight addFlight(@RequestBody CreateFlight flight) throws InvalidRequestException {
        return flightService.createFlight(flight);
    }

    @ResponseStatus(value= HttpStatus.UNPROCESSABLE_ENTITY, reason="Invalid data suplied")
    @ExceptionHandler(InvalidRequestException.class)
    public String dataIntegrityViolation(InvalidRequestException ex){
        return ex.getMessage();
    }
}
