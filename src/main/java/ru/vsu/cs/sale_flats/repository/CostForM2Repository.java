package ru.vsu.cs.sale_flats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.sale_flats.entity.CostForM2;

public interface CostForM2Repository extends JpaRepository<CostForM2, Integer> {
}