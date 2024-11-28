package com.ptsb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Data
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue
    private UUID id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private ZonedDateTime createdAt;
}
