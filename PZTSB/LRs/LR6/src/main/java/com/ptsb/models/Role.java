package com.ptsb.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames={"id", "name"}))
public class Role {

    @Id
    @GeneratedValue
    private UUID id;

    @Setter
    private String name;

    @Version
    private ZonedDateTime updatedAt;

    public Role(String name) {
        this.name = name;
    }

    public String toString(){
        return "Role(%s)".formatted(name);
    }

}
