package ru.vsu.cs.sale_flats.dto;

import java.util.List;

public record PaginationDto(
		List<Integer> pageSizes,
		List<Integer> pages,
		int page,
		int pageSize
) {
}
