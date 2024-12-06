package com.ptsb.controllers;

import com.ptsb.dtos.CreateTicket;
import com.ptsb.models.Ticket;
import com.ptsb.repositories.TicketRepository;
import com.ptsb.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @GetMapping("/")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{userId}")
    public List<Ticket> getUserTickets(@PathVariable UUID userId) {
        return ticketService.getAllUserTickets(userId);
    }

    @PostMapping("/")
    public Ticket createTicket(@RequestBody CreateTicket ticket) {
        return ticketService.createTicket(ticket);
    }
}
