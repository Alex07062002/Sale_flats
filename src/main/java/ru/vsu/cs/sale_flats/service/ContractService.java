package ru.vsu.cs.sale_flats.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sale_flats.dto.ContractTableDto;
import ru.vsu.cs.sale_flats.dto.PaginationDto;
import ru.vsu.cs.sale_flats.entity.Contract;
import ru.vsu.cs.sale_flats.entity.Person;
import ru.vsu.cs.sale_flats.repository.ContractRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ContractService implements PaginationProvider {
	final private ContractRepository contractRepository;


	public List<ContractTableDto> getContractsTable(Person user, int page, int pageSize) {
		Pageable pagination = Pageable.ofSize(pageSize)
				.withPage(page);
		return contractRepository
				.findByPersonId(user.getId(), pagination)
				.getContent()
				.stream()
				.map(this::mapContract)
				.toList();
	}


	private ContractTableDto mapContract(Contract contract) {
		return new ContractTableDto(
				contract.getId(),
				contract.getFlat().getId(),
				contract.getTypeOfPay(),
				contract.getStatusOfContract()
		);
	}

	public PaginationDto getPagination(Person person, int page, int pageSize) {
		final var count = contractRepository.countByPersonId(person.getId());
		return getPagination(count, page, pageSize);
	}
}
