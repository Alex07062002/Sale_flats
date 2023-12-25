package ru.vsu.cs.sale_flats.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.sale_flats.entity.Contract;
import ru.vsu.cs.sale_flats.entity.StatusType;
import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Integer> {

	@Override
	<S extends Contract> S save (S entity);

	List<Contract> getByFlatId(int id);

	Page<Contract> findByPersonId(int id, Pageable pageable);

	Page<Contract> getAll(Pageable pageable);
	long countByPersonId(int id);

	List<Contract> getByStatusOfContract(StatusType type);

	Optional<Contract> findContractById(Integer id);

	List<Contract> findAll();
}