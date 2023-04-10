package ru.vsu.cs.sale_flats.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contract", schema = "public", indexes = {
		@Index(name = "fki_foreign_key_flat", columnList = "flat_id"),
		@Index(name = "fki_p", columnList = "person_id")
})
public class Contract {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contract_id", nullable = false)
	private Integer id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "flat_id", nullable = false)
	private Flat flat;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;

	@Enumerated(EnumType.STRING)
	@Column(name = "type_of_pay", columnDefinition = "type_of_pay")
	private PayType typeOfPay;
	@Enumerated(EnumType.STRING)
	@Column(name = "status_of_contract", columnDefinition = "status_of_contract")
	private StatusType statusOfContract;

	public StatusType getStatusOfContract() {
		return statusOfContract;
	}

	public void setStatusOfContract(StatusType statusOfContract) {
		this.statusOfContract = statusOfContract;
	}

	public PayType getTypeOfPay() {
		return typeOfPay;
	}

	public void setTypeOfPay(PayType typeOfPay) {
		this.typeOfPay = typeOfPay;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Flat getFlat() {
		return flat;
	}

	public void setFlat(Flat flat) {
		this.flat = flat;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}