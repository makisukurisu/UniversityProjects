package com.ptsb.controllers;

import com.ptsb.models.City;
import com.ptsb.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }
}
