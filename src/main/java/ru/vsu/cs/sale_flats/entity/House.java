package ru.vsu.cs.sale_flats.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "house", schema = "public", indexes = {
		@Index(name = "fki_foreign_key", columnList = "city_id")
})
public class House {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "house_id", nullable = false)
	private Integer id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "city_id", nullable = false)
	private City city;

	@Size(max = 200)
	@NotNull
	@Column(name = "address", nullable = false, length = 200)
	private String address;

	@Size(max = 150)
	@NotNull
	@Column(name = "name", nullable = false, length = 150)
	private String name;

	@NotNull
	@Column(name = "constructionstartdate", nullable = false)
	private LocalDate constructionstartdate;

	@NotNull
	@Column(name = "constructioncompletiondate", nullable = false)
	private LocalDate constructioncompletiondate;

	@Column(name = "commissioning")
	private LocalDate commissioning;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getConstructionstartdate() {
		return constructionstartdate;
	}

	public void setConstructionstartdate(LocalDate constructionstartdate) {
		this.constructionstartdate = constructionstartdate;
	}

	public LocalDate getConstructioncompletiondate() {
		return constructioncompletiondate;
	}

	public void setConstructioncompletiondate(LocalDate constructioncompletiondate) {
		this.constructioncompletiondate = constructioncompletiondate;
	}

	public LocalDate getCommissioning() {
		return commissioning;
	}

	public void setCommissioning(LocalDate commissioning) {
		this.commissioning = commissioning;
	}

}