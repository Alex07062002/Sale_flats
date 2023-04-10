package ru.vsu.cs.sale_flats.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link ru.vsu.cs.sale_flats.entity.Person} entity
 */
public record PersonRegistrationDto(
		@Size(max = 100) @NotNull String name,
		@Size(max = 100) @NotNull String surname,
		@Size(max = 50) @NotNull String email,
		@Size(max = 50) @NotNull String login,
		@Size(max = 50) @NotNull String password,

		@Size(max = 50) @NotNull String passwordConfirm
) implements Serializable {
}