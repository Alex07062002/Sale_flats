package ru.vsu.cs.sale_flats.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "multiplier_for_cost", schema = "public")
public class MultiplierForCost {
	@Id
	@Column(name = "type", columnDefinition = "type_price not null")
	private String id;

	@NotNull
	@Column(name = "multiplier", nullable = false)
	private Float multiplier;

	public Object getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Float getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(Float multiplier) {
		this.multiplier = multiplier;
	}

}