package com.ptsb.repositories;

import com.ptsb.models.Ticket;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends ListCrudRepository<Ticket, UUID> {
    List<Ticket> findAllByUserId(UUID userId);
}
