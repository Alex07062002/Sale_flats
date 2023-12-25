package ru.vsu.cs.sale_flats.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sale_flats.dto.FlatDto;
import ru.vsu.cs.sale_flats.dto.PaginationDto;
import ru.vsu.cs.sale_flats.entity.Flat;
import ru.vsu.cs.sale_flats.entity.Floor;
import ru.vsu.cs.sale_flats.entity.OwnerType;
import ru.vsu.cs.sale_flats.entity.PriceType;
import ru.vsu.cs.sale_flats.repository.FlatRepository;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FlatService extends CrudService<Flat, Integer, FlatDto> implements PaginationProvider {

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

	@Override
	protected boolean validateAdd(FlatDto flatDto) {
		Integer numberOfRooms = flatDto.numberOfRooms();
		Integer entrance = flatDto.entrance();
		BigDecimal cost = flatDto.cost();
		BigDecimal square = flatDto.square();
		if (numberOfRooms == null || numberOfRooms <= 0){
			throw new IllegalArgumentException("Illegal parameter: numberOfRooms");
		}
		if (entrance == null || entrance < 1){
			throw new IllegalArgumentException("Illegal parameter: entrance");
		}
		if (cost == null || cost.doubleValue() <= 0){
			throw new IllegalArgumentException("Illegal parameter: cost");
		}
		if (square == null || square.doubleValue() <= 0){
			throw new IllegalArgumentException("Illegal parameter: square");
		}
		return true;
	}

	public void createFlat(FlatDto flatDto){
		if (!validateAdd(flatDto)){
			throw new IllegalArgumentException("Flat isn't created");
		}
		Flat flat = mapFromDto(flatDto);
		flatRepository.save(flat);
	}

	@Override
	protected boolean validateUpdate(FlatDto flatDto) {
		return true;
	}

	public void updateFlat(FlatDto flatDto){
		if (!validateUpdate(flatDto)){
			throw new IllegalArgumentException("Flat isn't updated");
		}
		Flat flat = mapFromDto(flatDto);
		flatRepository.save(flat);
	}

	@Override
	protected boolean validateDelete(FlatDto flatDto) {
		return true;
	}

	public void  deleteFlat(FlatDto flatDto){
		Flat flat = mapFromDto(flatDto);
		flatRepository.delete(flat);
	}

	public Optional<Flat> getFlatById(Integer id){
		return flatRepository.findFlatById(id);
	}

	public List<Flat> getAll(){
		return flatRepository.findAll();
	}

	@Override
	protected Flat mapFromDto(FlatDto flatDto) {
		Flat flat = new Flat();
		flat.setId(flatDto.id());
		flat.setNumberOfRooms(flatDto.numberOfRooms());
		flat.setEntrance(flatDto.entrance());
		flat.setCost(flatDto.cost());
		flat.setSquare(flatDto.square());
		flat.setTypeOwnerOfFlat(flatDto.typeOwnerOfFlat());
		flat.setTypePrice(flatDto.typePrice());
		return flat;
	}

	protected FlatDto mapToDto(Flat flat) {
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

	@Override
	public JpaRepository<Flat, Integer> getMainRepository() {
		return flatRepository;
	}
}
