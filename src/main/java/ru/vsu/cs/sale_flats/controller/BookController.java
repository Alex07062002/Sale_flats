package ru.vsu.cs.sale_flats.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vsu.cs.sale_flats.entity.City;
import ru.vsu.cs.sale_flats.entity.Flat;
import ru.vsu.cs.sale_flats.entity.Floor;
import ru.vsu.cs.sale_flats.entity.House;
import ru.vsu.cs.sale_flats.service.*;
import java.util.Map;

@Controller
@AllArgsConstructor
public class BookController {

	final CityService cityService;
	final HouseService houseService;
	final FloorService floorService;
	final FlatService flatService;
	final ContractService contractService;

	@GetMapping("/book")
	String cityList(
			@RequestParam(defaultValue = "5") int page,
			@RequestParam(defaultValue = "10") int pageSize,
			Map<String, Object> model
	) {
		var cities = cityService.getAvailableCities(page, pageSize);
		var pagination = cityService.getAvailableCitiesPagination(page, pageSize);
		model.put("cities", cities);
		model.put("pagination", pagination);
		return "city_list";
	}


	@GetMapping("/book/{city}")
	String houseList(
			@PathVariable City city,
			@RequestParam(defaultValue = "5") int page,
			@RequestParam(defaultValue = "10") int pageSize,
			Map<String, Object> model
	) {
		var houses = houseService.getAvailibleHouses(city, page, pageSize);
		var pagination = houseService.getAvailibleHosesPagination(city, page, pageSize);
		model.put("houses", houses);
		model.put("pagination", pagination);
		model.put("city", city.getId());
		return "house_list";
	}


	@GetMapping("/book/{city}/{house}")
	String floorList(
			@PathVariable City city,
			@PathVariable House house,
			@RequestParam(defaultValue = "5") int page,
			@RequestParam(defaultValue = "10") int pageSize,
			Map<String, Object> model
	) {
		var floors = floorService.getAvailableFloors(house, page, pageSize);
		var pagination = floorService.getAvailableFloorsPagination(house, page, pageSize);
		model.put("floors", floors);
		model.put("pagination", pagination);
		model.put("city", city.getId());
		model.put("house", house.getId());
		return "floor_list";
	}


	@GetMapping("/book/{city}/{house}/{floor}")
	String flatList(
			@PathVariable City city,
			@PathVariable House house,
			@PathVariable Floor floor,
			@RequestParam(defaultValue = "5") int page,
			@RequestParam(defaultValue = "10") int pageSize,
			Map<String, Object> model
	) {
		var flats = flatService.getAvailableFlats(floor, page, pageSize);
		var pagination = flatService.getAvailableFlatsFloors(floor, page, pageSize);
		model.put("flats", flats);
		model.put("pagination", pagination);
		model.put("city", city.getId());
		model.put("house", house.getId());
		model.put("floor", floor.getId());
		return "flat_list";
	}

	@GetMapping("/book/{city}/{house}/{floor}/{flat}")
	String orderPrepare(
			@PathVariable City city,
			@PathVariable House house,
			@PathVariable Floor floor,
			@PathVariable Flat flat,
			Map<String, Object> model
	) {

		return "order_create";
	}

	@PostMapping("/book/{city}/{house}/{floor}/{flat}")
	String orderCreate(
			@PathVariable City city,
			@PathVariable House house,
			@PathVariable Floor floor,
			@PathVariable Flat flat,
			Map<String, Object> model)
	{
		return "redirect:/contracts";
	}


}
