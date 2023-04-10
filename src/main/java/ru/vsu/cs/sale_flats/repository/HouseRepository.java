package ru.vsu.cs.sale_flats.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.vsu.cs.sale_flats.entity.House;

public interface HouseRepository extends JpaRepository<House, Integer> {

	boolean existsByCityId(int cityId);

	/*@Query("select h from House h where h.city.id = ?1 and exists " +
			"(select flt from Flat flt " +
			"join Floor fl on flt.floor=fl " +
			"where fl.house = h and not exists (select con from Contract con where con.flat = flt)and flt.typeOwnerOfFlat='free')")
	*/Page<House> findAllByCity_Id(int cityId, Pageable pageable);


	/*@Query(countQuery = "select count (h) from House h where h.city.id = ?1 and exists " +
			"(select flt from Flat flt " +
			"join Floor fl on flt.floor=fl " +
			"where fl.house = h and not exists (select con from Contract con where con.flat = flt)and flt.typeOwnerOfFlat='free')")
	*/long countAllByCity_Id(int cityId);
}