package ru.vsu.cs.sale_flats.service;

import ru.vsu.cs.sale_flats.dto.PaginationDto;

import java.util.stream.IntStream;

public interface PaginationProvider {
	default PaginationDto getPagination(long count, int page, int pageSize) {
		int endInclusive = (int) Math.ceil(count * 1.0 / pageSize) -1;
		return new PaginationDto(
				IntStream.range(1, 5).map((v) -> v * 5).boxed().toList(),
				IntStream.rangeClosed(0, endInclusive).boxed().toList(),
				page,
				pageSize
		);
	}
}
