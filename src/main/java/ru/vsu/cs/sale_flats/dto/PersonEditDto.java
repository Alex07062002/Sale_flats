package ru.vsu.cs.sale_flats.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link ru.vsu.cs.sale_flats.entity.Person} entity
 */
public record PersonEditDto(@Size(max = 100) @NotNull String nameSurname, @Size(max = 50) @NotNull String email,
							@Size(max = 50) @NotNull String login) implements Serializable {
}