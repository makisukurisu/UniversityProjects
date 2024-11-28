package com.ptsb.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Data
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn
    public User user;

    @ManyToOne
    @JoinColumn
    public Flight flight;

    @Getter
    @Setter
    public String pnr;

    @Getter
    @Setter
    public ZonedDateTime createdAt;
}
