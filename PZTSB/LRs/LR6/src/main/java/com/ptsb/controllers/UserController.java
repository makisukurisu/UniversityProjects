package com.ptsb.controllers;

import com.ptsb.dtos.CreateUser;
import com.ptsb.errors.DoesNotExistException;
import com.ptsb.models.User;
import com.ptsb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User getUser(@PathVariable UUID id) throws DoesNotExistException {
        return userService.getUserByID(id);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User addUser(@RequestBody CreateUser user){
        return userService.createUser(user);
    }

    @ResponseStatus(value=HttpStatus.CONFLICT, reason="Data integrity violation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void dataIntegrityViolation(DataIntegrityViolationException ex){
        System.out.println("Data integrity violation occurred");
        System.out.println(ex.getMessage());
    }

    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Entity not found")
    @ExceptionHandler(DoesNotExistException.class)
    public void entityNotFound(DoesNotExistException ex){
        System.out.println("Entity not found");
        System.out.println(ex.getMessage());
    }

}
