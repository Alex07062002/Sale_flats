package ru.vsu.cs.sale_flats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.sale_flats.entity.MultiplierForCost;

public interface MultiplierForCostRepository extends JpaRepository<MultiplierForCost, String> {
}