package ru.vsu.cs.sale_flats.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.vsu.cs.sale_flats.service.HouseService;

@Controller
@AllArgsConstructor
public class HouseController {
    final HouseService houseService;


}
