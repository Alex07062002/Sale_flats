package ru.vsu.cs.sale_flats.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sale_flats.dto.HouseTableDto;
import ru.vsu.cs.sale_flats.dto.PaginationDto;
import ru.vsu.cs.sale_flats.entity.City;
import ru.vsu.cs.sale_flats.entity.House;
import ru.vsu.cs.sale_flats.repository.FloorRepository;
import ru.vsu.cs.sale_flats.repository.HouseRepository;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HouseService extends CrudService<House,Integer,HouseTableDto> implements PaginationProvider {

	private final HouseRepository houseRepository;
	private final FloorRepository floorRepository;

	public JpaRepository<House, Integer> getMainRepository() {
		return houseRepository;
	}

	@Override
	protected boolean validateAdd(HouseTableDto houseTableDto) {

		String address = houseTableDto.address();
		String name = houseTableDto.name();
		LocalDate constructionstartdate = houseTableDto.constructionstartdate();
		LocalDate constructioncompletiondate = houseTableDto.constructioncompletiondate();

		if (address == null || address.isEmpty()) {
			throw new IllegalArgumentException("Missing house address");
		}

		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Missing house name");
		}

		if (constructionstartdate == null){
			throw new IllegalArgumentException("Missing house start date");
		}
		if (constructionstartdate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
						>= LocalDate.now().atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()) {
			throw new IllegalArgumentException("Impossible start date value.");
		}
		if (constructioncompletiondate == null){
			throw new IllegalArgumentException("Missing house complete date");
		}

		if (houseRepository.existsByName(name)) {
			throw new IllegalArgumentException("House exist");
		}

		return true;
	}

	public void addHouse(HouseTableDto houseDto) {
		if (!validateAdd(houseDto)){
			throw new IllegalArgumentException("House isn't created.");
		}
		try {
			House house = mapFromDto(houseDto);
			houseRepository.save(house);
		} catch (Exception e) {
			throw new RuntimeException("Unknown error", e);
		}
	}

	@Override
	protected boolean validateUpdate(HouseTableDto houseTableDto) {
		return true;
	}

	public void updateHouse(HouseTableDto houseTableDto) {
		if (!validateUpdate(houseTableDto)){
			throw new IllegalArgumentException("House isn't updated.");
		}
		House house = mapFromDto(houseTableDto);
		houseRepository.save(house);
	}


	@Override
	protected boolean validateDelete(HouseTableDto houseTableDto) {
		if (floorRepository.existsByHouseId(houseTableDto.id())) {
			throw new IllegalArgumentException("House in use");
		}
		return true;
	}

	public void removeHouse(HouseTableDto houseTableDto) {
		House house = mapFromDto(houseTableDto);
		houseRepository.delete(house);
	}

	public Optional<House> getHouseById(Integer id){
		return houseRepository.findHouseById(id);
	}

	public List<House> getAll(){
		return houseRepository.findAll();
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


	@Override
	protected House mapFromDto(HouseTableDto houseTableDto) { //FIXME City???
		House house = new House();
		house.setId(houseTableDto.id());
		house.setAddress(houseTableDto.address());
		house.setId(houseTableDto.id());
		house.setName(houseTableDto.name());
		house.setConstructionstartdate(houseTableDto.constructionstartdate());
		house.setConstructioncompletiondate(houseTableDto.constructioncompletiondate());
		house.setCommissioning(houseTableDto.commissioning());
		return house;
	}

	@Override
	protected HouseTableDto mapToDto(House house) {
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
