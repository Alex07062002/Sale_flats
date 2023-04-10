package ru.vsu.cs.sale_flats.dto;

import ru.vsu.cs.sale_flats.entity.OwnerType;
import ru.vsu.cs.sale_flats.entity.PriceType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link ru.vsu.cs.sale_flats.entity.Flat} entity
 */
public record FlatDto(Integer id, @NotNull Integer numberOfRooms, @NotNull Integer entrance, @NotNull BigDecimal cost,
					  @NotNull BigDecimal square, OwnerType typeOwnerOfFlat,
					  PriceType typePrice) implements Serializable {
}