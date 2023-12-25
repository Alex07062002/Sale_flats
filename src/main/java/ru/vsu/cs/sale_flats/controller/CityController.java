package ru.vsu.cs.sale_flats.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.vsu.cs.sale_flats.service.CityService;

@Controller
@AllArgsConstructor
public class CityController {
    final CityService cityService;

    public CityService getCityService() {
        return cityService;
    }
}

