package ru.vsu.cs.sale_flats.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sale_flats.dto.CityDto;
import ru.vsu.cs.sale_flats.dto.HouseTableDto;
import ru.vsu.cs.sale_flats.dto.PaginationDto;
import ru.vsu.cs.sale_flats.entity.City;
import ru.vsu.cs.sale_flats.entity.House;
import ru.vsu.cs.sale_flats.repository.CityRepository;
import ru.vsu.cs.sale_flats.repository.HouseRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class HouseService implements PaginationProvider {
	private final HouseRepository houseRepository;
	private final CityRepository cityRepository;

/*
	public void addHouse(CreateCityDto createCityDto) {
		String name = createCityDto.name();

		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Empty city");
		}

		Optional<City> cityFromDb = cityRepository.findByName(name);
		if (cityFromDb.isPresent()) {
			throw new IllegalArgumentException("City exist");
		}

		try {
			City city = new City();
			city.setName(name);
			cityRepository.save(city);
		} catch (Exception e) {
			throw new RuntimeException("Unknown error", e);
		}
	}*/

	public void editHouse(CityDto cityDto) {
		City city = mapCity(cityDto);
		cityRepository.save(city);
	}

	public void removeHouse(CityDto cityDto) {
		if (houseRepository.existsByCityId(cityDto.id())) {
			throw new IllegalArgumentException("City in use");
		}
		City city = mapCity(cityDto);
		cityRepository.delete(city);
	}

	private City mapCity(CityDto cityDto) {
		City city = new City();
		city.setId(cityDto.id());
		city.setName(cityDto.name());
		return city;
	}

	public List<HouseTableDto> getAvailibleHouses(City city, int page, int pageSize) {
		Pageable pagination = Pageable.ofSize(pageSize)
				.withPage(page);
		return houseRepository
				.findAllByCity_Id(city.getId(), pagination)
				.getContent()
				.stream()
				.map(this::mapToDto)
				.toList();
	}

	private HouseTableDto mapToDto(House house) {
		return new HouseTableDto(
				house.getId(),
				house.getAddress(),
				house.getName(),
				house.getConstructionstartdate(),
				house.getConstructioncompletiondate(),
				house.getCommissioning()
		);
	}

	public PaginationDto getAvailibleHosesPagination(City city, int page, int pageSize) {
		return getPagination(houseRepository.countAllByCity_Id(city.getId()), page, pageSize);
	}
}
