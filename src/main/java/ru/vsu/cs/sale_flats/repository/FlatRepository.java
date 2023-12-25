package ru.vsu.cs.sale_flats.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.vsu.cs.sale_flats.entity.Flat;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface FlatRepository extends JpaRepository<Flat, Integer> {

	@Override
	<S extends Flat> S save(S entity);


	/*@Query("select flt from Flat flt where flt.floor.id = ?1 and not exists " +
			"(select con from Contract con where con.flat = flt) and flt.typeOwnerOfFlat='free'")
	*/Page<Flat> findAllByFloor_Id(Integer floorId, Pageable pagination);

	/*@Query("select count(flt) from Flat flt where flt.floor.id = ?1 and not exists " +
			"(select con from Contract con where con.flat = flt) and flt.typeOwnerOfFlat='free'")
	*/long countByFloor_Id(Integer id);

	boolean existsByFloorId(Integer id);

	Optional<Flat> findFlatById(Integer id);

	List<Flat> findAll();
}