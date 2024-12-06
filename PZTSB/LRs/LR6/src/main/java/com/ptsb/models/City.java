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
@Table(name = "city", schema = "public")
public class City {
    @Id
    @GeneratedValue
    private UUID id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @Version
    private ZonedDateTime createdAt;
}
