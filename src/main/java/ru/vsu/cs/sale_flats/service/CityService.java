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
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityService extends CrudService<City, Integer, CityDto> implements PaginationProvider {

	private final CityRepository cityRepository;
	private final HouseRepository houseRepository;

	public JpaRepository<City, Integer> getMainRepository() {
		return cityRepository;
	}

	@Override
	protected boolean validateAdd(CityDto cityDto) {
		String name = cityDto.name();

		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Missing city name");
		}

		if (cityRepository.existsByName(name)) {
			throw new IllegalArgumentException("City exist");
		}

		return true;
	}

	public void addCity(CityDto cityDto) {
		if (!validateAdd(cityDto)){
			throw new IllegalArgumentException("City isn't created.");
		}
		try {
			City city = mapFromDto(cityDto);
			cityRepository.save(city);
		} catch (Exception e) {
			throw new RuntimeException("Unknown error", e);
		}
	}

	@Override
	protected boolean validateUpdate(CityDto cityDto) {
		return true;
	}

	public void updateCity(CityDto cityDto){
		if(!(validateUpdate(cityDto))){
			throw new IllegalArgumentException("City isn't updated.");
		}
		City city = mapFromDto(cityDto);
		cityRepository.save(city);
	}


	@Override
	protected boolean validateDelete(CityDto cityDto) {
		if (houseRepository.existsByCityId(cityDto.id())) {
			throw new IllegalArgumentException("City in use");
		}
		return true;
	}

	public void deleteCity(CityDto cityDto){
		City city = mapFromDto(cityDto);
		cityRepository.delete(city);
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
		return cityRepository
				.findAll(pagination)
				.getContent()
				.stream()
				.map(this::mapToDto)
				.toList();
	}

	public Optional<City> findById(int id){
		return cityRepository.findById(id);
	}

	public List<City> findAll(){
		return cityRepository.findAllByList();
	}

	public PaginationDto getAvailableCitiesPagination(int page, int pageSize) {
		return getPagination(cityRepository.count(), page, pageSize);
	}
}
