package ru.vsu.cs.sale_flats.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.sale_flats.entity.Contract;
import ru.vsu.cs.sale_flats.entity.StatusType;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Integer> {

	List<Contract> getByFlatId(int id);

	Page<Contract> findByPersonId(int id, Pageable pageable);
	long countByPersonId(int id);

	List<Contract> getByStatusOfContract(StatusType type);
}