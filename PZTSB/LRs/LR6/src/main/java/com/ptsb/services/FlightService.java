package com.ptsb.services;

import com.ptsb.dtos.CreateFlight;
import com.ptsb.errors.InvalidRequestException;
import com.ptsb.models.City;
import com.ptsb.models.Flight;
import com.ptsb.repositories.CityRepository;
import com.ptsb.repositories.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private CityService cityService;

    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }

    public List<Flight> getFlightsByFlightNumber(String flightNumber){
        return flightRepository.findAllByFlightNumber(flightNumber);
    }

    public Flight createFlight(CreateFlight createFlight) throws InvalidRequestException {
        Flight flight = new Flight(
            cityService.getCityById(createFlight.getDepartsFrom()).get(),
            cityService.getCityById(createFlight.getArrivesTo()).get(),
            createFlight.getDepartureTime(),
            createFlight.getArrivalTime(),
            createFlight.getPrice(),
            createFlight.getFlightNumber()
        );

        return flightRepository.save(flight);
    }
}
