package ru.vsu.cs.sale_flats.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link ru.vsu.cs.sale_flats.entity.Floor} entity
 */
public record FloorDto(Integer id,
                       @NotNull Integer floor) implements Serializable {
}