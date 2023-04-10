package ru.vsu.cs.sale_flats.service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryProvider<E, ID> {
	JpaRepository<E, ID> getMainRepository();
}
