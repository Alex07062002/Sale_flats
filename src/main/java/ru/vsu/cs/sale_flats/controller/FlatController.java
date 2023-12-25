package ru.vsu.cs.sale_flats.controller;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.vsu.cs.sale_flats.service.FlatService;

@Controller
@AllArgsConstructor
public class FlatController {

    final FlatService flatService;


}
