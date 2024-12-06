package com.ptsb.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Setter
@Getter
@Data
@Component
public class CreateFlight {
    UUID departsFrom;
    UUID arrivesTo;

    String departureTime;
    String arrivalTime;

    int price;

    String flightNumber;
}
