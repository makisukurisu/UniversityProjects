package com.ptsb.services;

import com.ptsb.dtos.CreateTicket;
import com.ptsb.errors.InvalidRequestException;
import com.ptsb.models.Flight;
import com.ptsb.models.Ticket;
import com.ptsb.models.User;
import com.ptsb.repositories.FlightRepository;
import com.ptsb.repositories.TicketRepository;
import com.ptsb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FlightRepository flightRepository;

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public List<Ticket> getAllUserTickets(UUID userId){
        return ticketRepository.findAllByUserId(userId);
    }

    public Ticket createTicket(CreateTicket createTicket){
        Optional<User> user = userRepository.getUserById(createTicket.getUserId());
        Optional<Flight> flight = flightRepository.getFlightById(createTicket.getFlightId());
        if(user.isEmpty()){
            throw new InvalidRequestException("User not found");
        }
        if(flight.isEmpty()){
            throw new InvalidRequestException("Flight not found");
        }

        Ticket ticket = new Ticket(
                user.get(),
                flight.get(),
                createTicket.getPnr()
        );
        return ticketRepository.save(ticket);
    }
}
