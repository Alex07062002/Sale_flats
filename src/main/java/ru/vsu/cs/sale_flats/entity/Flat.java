package ru.vsu.cs.sale_flats.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "flat", schema = "public")
public class Flat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flat_id", nullable = false)
	private Integer id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "floor_id", nullable = false)
	private Floor floor;

	@NotNull
	@Column(name = "number_of_rooms", nullable = false)
	private Integer numberOfRooms;

	@NotNull
	@Column(name = "entrance", nullable = false)
	private Integer entrance;

	@NotNull
	@Column(name = "cost", nullable = false)
	private BigDecimal cost;

	@NotNull
	@Column(name = "square", nullable = false)
	private BigDecimal square;
	@Enumerated(EnumType.STRING)
	@Column(name = "type_owner_of_flat", columnDefinition = "type_owner_of_flat not null")
	private OwnerType typeOwnerOfFlat;

	@Enumerated(EnumType.STRING)
	@Column(name = "type_price", columnDefinition = "type_price not null")
	private PriceType typePrice;

	public PriceType getTypePrice() {
		return typePrice;
	}

	public void setTypePrice(PriceType typePrice) {
		this.typePrice = typePrice;
	}

	public OwnerType getTypeOwnerOfFlat() {
		return typeOwnerOfFlat;
	}

	public void setTypeOwnerOfFlat(OwnerType typeOwnerOfFlat) {
		this.typeOwnerOfFlat = typeOwnerOfFlat;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public Integer getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(Integer numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public Integer getEntrance() {
		return entrance;
	}

	public void setEntrance(Integer entrance) {
		this.entrance = entrance;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getSquare() {
		return square;
	}

	public void setSquare(BigDecimal square) {
		this.square = square;
	}

}