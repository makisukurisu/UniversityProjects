package com.ptsb.models;

import com.ptsb.dtos.CreateTicket;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Data
@Entity
@Table(name = "ticket", schema = "public")
public class Ticket {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    public User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    public Flight flight;

    @Getter
    @Setter
    public String pnr;

    @Getter
    @Setter
    public ZonedDateTime createdAt;

    public Ticket() {

    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = ZonedDateTime.now();
    }

    public Ticket(
            User user,
            Flight flight,
            String pnr
    )
    {
        this.user = user;
        this.flight = flight;
        this.pnr = pnr;
    }
}
