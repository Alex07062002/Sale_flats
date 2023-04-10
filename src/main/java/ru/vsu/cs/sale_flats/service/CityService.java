package ru.vsu.cs.sale_flats.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sale_flats.dto.CityDto;
import ru.vsu.cs.sale_flats.dto.PaginationDto;
import ru.vsu.cs.sale_flats.entity.City;
import ru.vsu.cs.sale_flats.repository.CityRepository;
import ru.vsu.cs.sale_flats.repository.HouseRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CityService extends CrudService<City, Integer, CityDto> implements PaginationProvider {

	private final CityRepository mainRepository;
	private final HouseRepository houseRepository;


	@Override
	public JpaRepository<City, Integer> getMainRepository() {
		return mainRepository;
	}

	@Override
	protected boolean validateAdd(CityDto cityDto) {
		String name = cityDto.name();

		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Missing city name");
		}

		if (mainRepository.existsByName(name)) {
			throw new IllegalArgumentException("City exist");
		}

		return true;
	}

	@Override
	protected boolean validateUpdate(CityDto cityDto) {
		return true;
	}

	@Override
	protected boolean validateDelete(CityDto cityDto) {
		if (houseRepository.existsByCityId(cityDto.id())) {
			throw new IllegalArgumentException("City in use");
		}

		return true;
	}

	@Override
	protected City mapFromDto(CityDto cityDto) {
		City city = new City();
		city.setId(cityDto.id());
		city.setName(cityDto.name());
		return city;
	}

	@Override
	protected CityDto mapToDto(City city) {
		return new CityDto(city.getId(), city.getName());
	}

	public List<CityDto> getAvailableCities(int page, int pageSize) {
		Pageable pagination = Pageable.ofSize(pageSize)
				.withPage(page);
		return mainRepository
				.findAll(pagination)
				.getContent()
				.stream()
				.map(this::mapToDto)
				.toList();
	}

	public PaginationDto getAvailableCitiesPagination(int page, int pageSize) {
		return getPagination(mainRepository.count(), page, pageSize);
	}
}
