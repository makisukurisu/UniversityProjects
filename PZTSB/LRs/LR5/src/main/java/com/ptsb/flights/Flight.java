package com.ptsb.flights;

import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

@Component
public class Flight {
    private UUID id;

    private String departsFrom;
    private String arrivesTo;

    private ZonedDateTime departureTime;
    private ZonedDateTime arrivalTime;

    private int price;

    private String flightNumber;

    private ZonedDateTime createdAt;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = Objects.requireNonNullElseGet(id, UUID::randomUUID);
    }
    public String getDepartsFrom() {
        return departsFrom;
    }
    public void setDepartsFrom(String departsFrom) {
        this.departsFrom = departsFrom;
    }
    public String getArrivesTo() {
        return arrivesTo;
    }
    public void setArrivesTo(String arrivesTo) {
        this.arrivesTo = arrivesTo;
    }
    public ZonedDateTime getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(ZonedDateTime departureTime) {
        this.departureTime = departureTime;
    }
    public ZonedDateTime getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(ZonedDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getFlightNumber() {
        return flightNumber;
    }
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt() {
        if (createdAt != null) {return;}
        this.createdAt = ZonedDateTime.now();
    }

    Flight(){
        this.setId(null);
        this.setCreatedAt();
    };
    Flight(
            UUID id,
            String departsFrom,
            String arrivesTo,
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
