package ru.vsu.cs.sale_flats.dto;

import ru.vsu.cs.sale_flats.entity.PayType;
import ru.vsu.cs.sale_flats.entity.StatusType;

import java.io.Serializable;

/**
 * A DTO for the {@link ru.vsu.cs.sale_flats.entity.Contract} entity
 */
public record ContractTableDto(
		Integer id,
		Integer flatId,
		PayType typeOfPay,
		StatusType statusOfContract
) implements Serializable {
}