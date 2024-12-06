package com.ptsb.services;

import com.ptsb.dtos.CreateUser;
import com.ptsb.models.User;
import com.ptsb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.ptsb.errors.DoesNotExistException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User createUser(CreateUser user) throws org.springframework.dao.DataIntegrityViolationException {
        User newUser = new User(
            user.getFirstName(),
            user.getLastName(),
            user.getUsername(),
            user.getPassword()
        );
        return userRepository.save(newUser);
    }

    public User getUserByID(UUID id) throws DoesNotExistException {
        Optional<User> user = userRepository.getUserById(id);
        if(user.isPresent()) {
            return user.get();
        }
        throw new DoesNotExistException(
            String.format("User with ID %s not found", id)
        );
    }
}
