package ru.vsu.cs.sale_flats.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.vsu.cs.sale_flats.entity.City;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
	Optional<City> findByName(String name);


	boolean existsByName(String name);

	/*@Query("select c from City c where exists " +
				"(select flt from Flat flt " +
				"join Floor fl on flt.floor=fl " +
				"join House h on fl.house=h " +
				"where h.city=c and not exists (select con from Contract con where con.flat = flt) and flt.typeOwnerOfFlat='free')")
	*/Page<City> findAll(Pageable pageable);


	/*@Query(countQuery = "select count (c) from City c where exists " +
			"(select flt from Flat flt " +
			"join Floor fl on flt.floor=fl " +
			"join House h on fl.house=h " +
			"where h.city=c and not exists (select con from Contract con where con.flat = flt) and flt.typeOwnerOfFlat='free')")
	*/long count();
}