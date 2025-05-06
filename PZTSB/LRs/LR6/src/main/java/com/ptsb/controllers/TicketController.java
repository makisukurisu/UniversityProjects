package com.ptsb.controllers;

import com.ptsb.dtos.CreateTicket;
import com.ptsb.models.Ticket;
import com.ptsb.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<Ticket> getUserTickets(@PathVariable UUID userId) {
        return ticketService.getAllUserTickets(userId);
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public Ticket createTicket(@RequestBody CreateTicket ticket) {
        return ticketService.createTicket(ticket);
    }
}
