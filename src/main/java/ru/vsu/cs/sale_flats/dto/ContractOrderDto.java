package ru.vsu.cs.sale_flats.dto;

import ru.vsu.cs.sale_flats.entity.OwnerType;
import ru.vsu.cs.sale_flats.entity.PayType;
import ru.vsu.cs.sale_flats.entity.PriceType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A DTO for the {@link ru.vsu.cs.sale_flats.entity.Contract} entity
 */
public record ContractOrderDto(
		Integer id,
		Integer flatId,
		@Size(max = 100) @NotNull String flatFloorHouseCityName,
		@Size(max = 200) @NotNull String flatFloorHouseAddress,
		@Size(max = 150) @NotNull String flatFloorHouseName,
		@NotNull LocalDate flatFloorHouseConstructionstartdate,
		@NotNull LocalDate flatFloorHouseConstructioncompletiondate,
		LocalDate flatFloorHouseCommissioning,
		@NotNull Integer flatFloorFloor,
		@NotNull Integer flatNumberOfRooms,
		@NotNull Integer flatEntrance,
		@NotNull BigDecimal flatCost,
		@NotNull BigDecimal flatSquare,
		OwnerType flatTypeOwnerOfFlat,
		PriceType flatTypePrice,
		PayType typeOfPay
) implements Serializable {
}