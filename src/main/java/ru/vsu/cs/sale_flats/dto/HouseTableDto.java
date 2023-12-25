package ru.vsu.cs.sale_flats.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link ru.vsu.cs.sale_flats.entity.House} entity
 */
public record HouseTableDto(Integer id, @Size(max = 200) @NotNull String address, @Size(max = 150) @NotNull String name,
							@NotNull LocalDate constructionstartdate,
							@NotNull LocalDate constructioncompletiondate,
							LocalDate commissioning) implements Serializable {
}