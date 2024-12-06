package com.ptsb.repositories;

import com.ptsb.models.Flight;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FlightRepository extends ListCrudRepository<Flight, UUID> {
    List<Flight> findAllByFlightNumber(String flightNumber);

    Optional<Flight> getFlightById(UUID flightId);
}
