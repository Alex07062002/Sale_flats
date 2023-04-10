package ru.vsu.cs.sale_flats.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public abstract class CrudService<E, ID, Dto> implements RepositoryProvider<E,ID>{


	Dto getById(ID id) {
		Optional<E> optionalCity = getMainRepository().findById(id);
		E city = optionalCity.orElseThrow(() -> new IllegalArgumentException("Missing exist"));
		return mapToDto(city);
	}


	List<Dto> getAll(int size, int pageNumber) {
		Pageable page = Pageable.ofSize(size).withPage(pageNumber);

		return getMainRepository().findAll(page)
				.getContent()
				.stream()
				.map(this::mapToDto)
				.toList();
	}

	protected abstract boolean validateAdd(Dto dto);

	protected abstract boolean validateUpdate(Dto dto);

	protected abstract boolean validateDelete(Dto dto);


	public void add(Dto dto) {
		if (!validateAdd(dto)) {
			throw new IllegalArgumentException("Bad request");
		}

		E entity = mapFromDto(dto);
		getMainRepository().save(entity);
	}

	public void update(Dto dto) {
		if (!validateUpdate(dto)) {
			throw new IllegalArgumentException("Bad request");
		}

		E entity = mapFromDto(dto);
		getMainRepository().save(entity);
	}

	public void delete(Dto dto) {
		if (!validateDelete(dto)) {
			throw new IllegalArgumentException("Bad request");
		}

		E entity = mapFromDto(dto);
		getMainRepository().delete(entity);
	}

	protected abstract E mapFromDto(Dto dto);

	protected abstract Dto mapToDto(E e);

}
