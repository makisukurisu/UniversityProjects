package com.ptsb.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Data
@Component
public class CreateUser {
    private String firstName;

    private String lastName;

    private String username;

    private String password;
}
