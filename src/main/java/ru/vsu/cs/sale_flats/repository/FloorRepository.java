package ru.vsu.cs.sale_flats.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.vsu.cs.sale_flats.entity.Floor;

public interface FloorRepository extends JpaRepository<Floor, Integer> {
	/*@Query("select count (fl) from Floor fl where fl.house.id = ?1 and exists " +
			"(select flt from Flat flt " +
			"where flt.floor = fl and not exists (select con from Contract con where con.flat = flt) and flt.typeOwnerOfFlat='free')")
	*/long countAllByHouse_Id(int houseId);

	/*@Query("select fl from Floor fl where fl.house.id = ?1 and exists " +
			"(select flt from Flat flt " +
			"where flt.floor = fl and not exists (select con from Contract con where con.flat = flt) and flt.typeOwnerOfFlat='free')")
	*/Page<Floor> findAllByHouse_Id(int house, Pageable pagination);
}