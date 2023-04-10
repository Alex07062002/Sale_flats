package ru.vsu.cs.sale_flats.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sale_flats.dto.FloorDto;
import ru.vsu.cs.sale_flats.dto.PaginationDto;
import ru.vsu.cs.sale_flats.entity.Floor;
import ru.vsu.cs.sale_flats.entity.House;
import ru.vsu.cs.sale_flats.repository.FloorRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FloorService implements PaginationProvider {

	private final FloorRepository floorRepository;

	public List<FloorDto> getAvailableFloors(House house, int page, int pageSize) {
		Pageable pagination = Pageable.ofSize(pageSize)
				.withPage(page);
		return floorRepository
				.findAllByHouse_Id(house.getId(), pagination)
				.getContent()
				.stream()
				.map(this::mapToDto)
				.toList();
	}

	private FloorDto mapToDto(Floor floor) {
		return new FloorDto(
				floor.getId(),
				floor.getFloor()
		);
	}

	public PaginationDto getAvailableFloorsPagination(House house, int page, int pageSize) {
		return getPagination(floorRepository.countAllByHouse_Id(house.getId()), page, pageSize);
	}
}
