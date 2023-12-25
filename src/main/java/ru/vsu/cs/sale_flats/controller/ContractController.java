package ru.vsu.cs.sale_flats.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vsu.cs.sale_flats.entity.Person;
import ru.vsu.cs.sale_flats.service.ContractService;

import java.util.Map;

@Controller
@AllArgsConstructor
public class ContractController {

	final ContractService contractService;


	@GetMapping("/contracts")
	String contracts(
			@AuthenticationPrincipal Person person,
			@RequestParam(defaultValue = "5") int page,
			@RequestParam(defaultValue = "10") int pageSize,
			Map<String, Object> model
	) {
		var orders = contractService.getContractsTable(person, page, pageSize);
		var pagination = contractService.getPagination(person, page, pageSize);
		model.put("orders", orders);
		model.put("pagination", pagination);
		return "contracts";
	}


}
