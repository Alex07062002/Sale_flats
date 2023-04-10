package ru.vsu.cs.sale_flats.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vsu.cs.sale_flats.dto.PersonEditDto;
import ru.vsu.cs.sale_flats.dto.PersonRegistrationDto;
import ru.vsu.cs.sale_flats.entity.Person;
import ru.vsu.cs.sale_flats.service.UserService;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class UserController {
	private final UserService userService;

	@GetMapping("/registration")
	String registration() {
		return "registration";
	}

	@PostMapping("/registration")
	String registration(@Valid PersonRegistrationDto personRegistrationDto) {
		userService.register(personRegistrationDto);
		return "redirect:/login";
	}


	@PostMapping("/edit/profile")
	String editProfile(@AuthenticationPrincipal Person person, PersonEditDto personEditDto) {
		userService.updatePerson(person, personEditDto);
		return "account";
	}


}
