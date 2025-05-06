package com.ptsb.repositories;

import com.ptsb.models.Role;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends ListCrudRepository<Role, UUID> {
    Role getRoleByName(String roleName);
}
