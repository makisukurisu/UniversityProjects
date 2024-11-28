package com.ptsb.models;

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
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "departsFrom_id")
    private City departsFrom;

    @ManyToOne
    @JoinColumn(name = "arrivesTo_id")
    private City arrivesTo;

    @Setter
    private ZonedDateTime departureTime;
    @Setter
    private ZonedDateTime arrivalTime;

    @Setter
    private int price;

    @Setter
    private String flightNumber;

    private ZonedDateTime createdAt;

    public void setId(UUID id) {
        this.id = Objects.requireNonNullElseGet(id, UUID::randomUUID);
    }

    public void setCreatedAt() {
        if (createdAt != null) {return;}
        this.createdAt = ZonedDateTime.now();
    }

    public Flight(){
        this.setId(null);
        this.setCreatedAt();
    };

    public Flight(
            UUID id,
            City departsFrom,
            City arrivesTo,
            String departureTime,
            String arrivalTime,
            int price,
            String flightNumber
    ){
        this.id = Objects.requireNonNullElseGet(id, UUID::randomUUID);
        this.departsFrom = departsFrom;
        this.arrivesTo = arrivesTo;
        this.departureTime = ZonedDateTime.parse(departureTime);
        this.arrivalTime = ZonedDateTime.parse(arrivalTime);
        this.price = price;
        this.flightNumber = flightNumber;
        this.createdAt = ZonedDateTime.now();
    }

}
