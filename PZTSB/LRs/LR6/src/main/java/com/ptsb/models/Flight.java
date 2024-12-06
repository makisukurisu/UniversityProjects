package com.ptsb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Data
@Entity
@Table(name = "flight", schema="public")
public class Flight {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departs_from_id")
    private City departsFrom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "arrives_to_id")
    private City arrivesTo;

    @Setter
    private ZonedDateTime departureTime;
    @Setter
    private ZonedDateTime arrivalTime;

    @Setter
    private int price;

    @Setter
    private String flightNumber;

    // Didn't want to save otherwise
    @Version
    @JsonIgnore
    private Long version;

    private ZonedDateTime createdAt;

    public Flight() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = ZonedDateTime.now();
    }

    public Flight(
        City departsFrom,
        City arrivesTo,
        String departureTime,
        String arrivalTime,
        int price,
        String flightNumber
    ) {
        this.setDepartsFrom(departsFrom);
        this.setArrivesTo(arrivesTo);
        this.departureTime = ZonedDateTime.parse(departureTime);
        this.arrivalTime = ZonedDateTime.parse(arrivalTime);
        this.price = price;
        this.flightNumber = flightNumber;
    }

}
