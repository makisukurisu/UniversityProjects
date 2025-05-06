package com.ptsb.services;

import com.ptsb.dtos.CreateUser;
import com.ptsb.models.User;
import com.ptsb.repositories.RoleRepository;
import com.ptsb.repositories.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import com.ptsb.errors.DoesNotExistException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Data
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

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
        newUser.setRoles(Set.of(roleRepository.getRoleByName("ROLE_USER")));
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var result = userRepository.getUserByUsername(username);
        if(result.isEmpty()) {
            throw new UsernameNotFoundException("Could not find");
        }
        var user = result.get();

        var permStream = user.getRoles();
        var authorities = permStream.stream().map(
                role -> new SimpleGrantedAuthority(role.getName())
        );

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getHashedPassword(),
                authorities.toList()
        );
    }
}
