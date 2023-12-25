package ru.vsu.cs.sale_flats.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sale_flats.dto.ContractTableDto;
import ru.vsu.cs.sale_flats.dto.PaginationDto;
import ru.vsu.cs.sale_flats.entity.Contract;
import ru.vsu.cs.sale_flats.entity.Person;
import ru.vsu.cs.sale_flats.repository.ContractRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ContractService extends CrudService<Contract, Integer, ContractTableDto> implements PaginationProvider {

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

	public List<ContractTableDto> getAll(int page, int pageSize) {
		Pageable pagination = Pageable.ofSize(pageSize)
				.withPage(page);
		return contractRepository
				.getAll(pagination)
				.getContent()
				.stream()
				.map(this::mapContract)
				.toList();
	}

	@Override
	protected boolean validateAdd(ContractTableDto contractTableDto) {
		return true;
	}

	@Override
	protected boolean validateUpdate(ContractTableDto contractTableDto) {
		return true;
	}

	@Override
	protected boolean validateDelete(ContractTableDto contractTableDto) {
		return true;
	}

	@Override
	protected Contract mapFromDto(ContractTableDto contractTableDto) {
		return null;
	}

	@Override
	protected ContractTableDto mapToDto(Contract contract) {
		return null;
	}

	public void updateContract(Contract contract, ContractTableDto contractTableDto) { //TODO Controller
		contract.setStatusOfContract(contractTableDto.statusOfContract());
		contractRepository.save(contract);
	}

	public Optional<Contract> getContractById(Integer id){
		return contractRepository.findContractById(id);
	}

	public List<Contract> getAll(){
		return contractRepository.findAll();
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

	@Override
	public JpaRepository<Contract, Integer> getMainRepository() {
		return contractRepository;
	}
}
