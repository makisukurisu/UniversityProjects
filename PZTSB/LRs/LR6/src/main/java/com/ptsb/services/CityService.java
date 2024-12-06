package com.ptsb.services;

import com.ptsb.models.City;
import com.ptsb.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;

    public List<City> getAllCities(){
        return cityRepository.findAll();
    }

    public Optional<City> getCityById(UUID id){
        return cityRepository.findById(id);
    }
}
