package ru.vsu.cs.sale_flats.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link ru.vsu.cs.sale_flats.entity.City} entity
 */
public record CityDto(Integer id, @Size(max = 100) @NotNull String name) implements Serializable {
}