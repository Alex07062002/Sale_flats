package ru.vsu.cs.sale_flats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import ru.vsu.cs.sale_flats.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

	@Override
	<S extends Person> S save(S entity);

	Optional<Person> findByLogin(String username);

	boolean existsByEmail(String email);
	boolean existsByLogin(String login);
}