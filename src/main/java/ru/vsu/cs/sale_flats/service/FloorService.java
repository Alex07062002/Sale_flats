package ru.vsu.cs.sale_flats.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sale_flats.dto.FloorDto;
import ru.vsu.cs.sale_flats.dto.PaginationDto;
import ru.vsu.cs.sale_flats.entity.Floor;
import ru.vsu.cs.sale_flats.entity.House;
import ru.vsu.cs.sale_flats.repository.FlatRepository;
import ru.vsu.cs.sale_flats.repository.FloorRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FloorService extends CrudService<Floor, Integer, FloorDto> implements PaginationProvider {

	private final FloorRepository floorRepository;
	private final FlatRepository flatRepository;

	@Override
	public JpaRepository<Floor, Integer> getMainRepository() {
		return floorRepository;
	}

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

	@Override
	protected boolean validateAdd(FloorDto floorDto) {
		Integer floor = floorDto.floor();
		if (floor == null || floor < 0){
			throw new IllegalArgumentException("Illegal argument: floor");
		}
		return true;
	}

	public void addFloor(FloorDto floorDto){
		if (!validateAdd(floorDto)){
			throw new IllegalArgumentException("Floor isn't created");
		}
		Floor floor = mapFromDto(floorDto);
		floorRepository.save(floor);
	}

	@Override
	protected boolean validateUpdate(FloorDto floorDto) {
		return true;
	}

	public void updateFloor(FloorDto floorDto){
		if (!validateUpdate(floorDto)){
			throw new IllegalArgumentException("Floor isn't updated");
		}
		Floor floor = mapFromDto(floorDto);
		floorRepository.save(floor);
	}


	@Override
	protected boolean validateDelete(FloorDto floorDto) {
		if (flatRepository.existsByFloorId(floorDto.id())) {
			throw new IllegalArgumentException("Floor in use");
		}
		return true;
	}

	public void deleteFloor(FloorDto floorDto){
		Floor floor = mapFromDto(floorDto);
		floorRepository.delete(floor);
	}

	public Optional<Floor> getFloorById(Integer id){
		return floorRepository.findFloorById(id);
	}

	public List<Floor> getAll(){
		return floorRepository.findAll();
	}

	@Override
	protected Floor mapFromDto(FloorDto floorDto) { //FIXME House???
		Floor floor = new Floor();
		floor.setId(floorDto.id());
		floor.setFloor(floorDto.floor());
		return floor;
	}

	protected FloorDto mapToDto(Floor floor) {
		return new FloorDto(
				floor.getId(),
				floor.getFloor()
		);
	}

	public PaginationDto getAvailableFloorsPagination(House house, int page, int pageSize) {
		return getPagination(floorRepository.countAllByHouse_Id(house.getId()), page, pageSize);
	}
}
