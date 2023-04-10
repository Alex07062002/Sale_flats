package ru.vsu.cs.sale_flats.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sale_flats.dto.FlatDto;
import ru.vsu.cs.sale_flats.dto.PaginationDto;
import ru.vsu.cs.sale_flats.entity.Flat;
import ru.vsu.cs.sale_flats.entity.Floor;
import ru.vsu.cs.sale_flats.repository.FlatRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FlatService implements PaginationProvider {

	private final FlatRepository flatRepository;

	public List<FlatDto> getAvailableFlats(Floor floor, int page, int pageSize) {
		Pageable pagination = Pageable.ofSize(pageSize)
				.withPage(page);
		return flatRepository
				.findAllByFloor_Id(floor.getId(), pagination)
				.getContent()
				.stream()
				.map(this::mapToDto)
				.toList();
	}

	private FlatDto mapToDto(Flat flat) {
		return new FlatDto(
				flat.getId(),
				flat.getNumberOfRooms(),
				flat.getEntrance(),
				flat.getCost(),
				flat.getSquare(),
				flat.getTypeOwnerOfFlat(),
				flat.getTypePrice()
		);
	}

	public PaginationDto getAvailableFlatsFloors(Floor floor, int page, int pageSize) {
		return getPagination(flatRepository.countByFloor_Id(floor.getId()), page, pageSize);
	}
}
