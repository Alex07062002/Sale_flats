package ru.vsu.cs.sale_flats.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sale_flats.dto.PersonEditDto;
import ru.vsu.cs.sale_flats.dto.PersonRegistrationDto;
import ru.vsu.cs.sale_flats.entity.Person;
import ru.vsu.cs.sale_flats.entity.UserType;
import ru.vsu.cs.sale_flats.exception.RegistrationException;
import ru.vsu.cs.sale_flats.repository.PersonRepository;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	private final PersonRepository personRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return personRepository.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	public void register(PersonRegistrationDto dto) {
		if (personRepository.existsByLogin(dto.login())) {
			throw new RegistrationException("Пользователь существет", dto);
		}

		if (personRepository.existsByEmail(dto.email())) {
			throw new RegistrationException("Пользователь существет", dto);
		}

		if (!dto.password().equals(dto.passwordConfirm())) {
			throw new RegistrationException("Пароли не совпадают", dto);
		}

		Person person = new Person();
		person.setNameSurname(dto.name() + " " + dto.surname());
		person.setEmail(dto.email());
		person.setLogin(dto.login());
		person.setPassword(dto.password());
		person.setRole(UserType.user);
		personRepository.save(person);
	}

	public void updatePerson(Person person, PersonEditDto personEditDto) {
		person.setEmail(personEditDto.email());
		person.setNameSurname(personEditDto.nameSurname());
		person.setLogin(personEditDto.login());
		personRepository.save(person);
	}
}
