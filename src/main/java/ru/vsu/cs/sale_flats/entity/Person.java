package ru.vsu.cs.sale_flats.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "person", schema = "public", indexes = {
		@Index(name = "unique_login", columnList = "login", unique = true)
})
public class Person implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id", nullable = false)
	private Integer id;

	@Size(max = 100)
	@NotNull
	@Column(name = "name_surname", nullable = false, length = 100)
	private String nameSurname;

	@Size(max = 50)
	@NotNull
	@Column(name = "email", nullable = false, length = 50)
	private String email;

	@Size(max = 50)
	@NotNull
	@Column(name = "login", nullable = false, length = 50)
	private String login;

	@Size(max = 100)
	@NotNull
	@Column(name = "password", nullable = false, length = 50)
	private String password;

	@Column(name = "role", columnDefinition = "person_role not null")
	@Enumerated(EnumType.STRING)
	private UserType role;

	public UserType getRole() {
		return role;
	}

	public void setRole(UserType role) {
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameSurname() {
		return nameSurname;
	}

	public void setNameSurname(String nameSurname) {
		this.nameSurname = nameSurname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(role);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}