package com.ptsb;

import com.ptsb.models.Role;
import com.ptsb.models.User;
import com.ptsb.repositories.RoleRepository;
import com.ptsb.repositories.UserRepository;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class CreateSuperuser implements SmartInitializingSingleton {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public void createRoles(){
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        roleRepository.save(adminRole);
        roleRepository.save(userRole);
    }

    public void createUser(String username, String password){
        User user = new User(
                "Admin",
                "Admin",
                username,
                password
        );

        Role adminRole = roleRepository.getRoleByName("ROLE_ADMIN");

        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        user.setRoles(roles);

        userRepository.save(user);
    }

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void afterSingletonsInstantiated() {
        boolean create_roles = System.getenv().getOrDefault("CREATE_ROLES", "0").equals("1");
        boolean create_superuser = System.getenv().getOrDefault("CREATE_SUPERUSER", "0").equals("1");

        if (create_roles){
            createRoles();
        }
        if (create_superuser) {
            String username = System.getenv("SUPERUSER_USERNAME");
            String password = System.getenv("SUPERUSER_PASSWORD");
            if (username == null || password == null) {
                return;
            }
            if (userRepository.getUserByUsername(username).isPresent()) {
                return;
            }

            createUser(username, password);
        }
    }
}
