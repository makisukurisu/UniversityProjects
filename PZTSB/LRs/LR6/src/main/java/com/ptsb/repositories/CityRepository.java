package com.ptsb.repositories;

import com.ptsb.models.City;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CityRepository extends ListCrudRepository<City, UUID> {
    City getById(UUID departsFrom);
}
