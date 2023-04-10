package ru.vsu.cs.sale_flats.exception;

import ru.vsu.cs.sale_flats.dto.PersonRegistrationDto;

public class RegistrationException  extends RuntimeException{
	public final PersonRegistrationDto personRegistrationDto;

	public RegistrationException(PersonRegistrationDto personRegistrationDto) {
		this.personRegistrationDto = personRegistrationDto;
	}

	public RegistrationException(String message, PersonRegistrationDto personRegistrationDto) {
		super(message);
		this.personRegistrationDto = personRegistrationDto;
	}

	public RegistrationException(String message, Throwable cause, PersonRegistrationDto personRegistrationDto) {
		super(message, cause);
		this.personRegistrationDto = personRegistrationDto;
	}

	public RegistrationException(Throwable cause, PersonRegistrationDto personRegistrationDto) {
		super(cause);
		this.personRegistrationDto = personRegistrationDto;
	}

	public RegistrationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, PersonRegistrationDto personRegistrationDto) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.personRegistrationDto = personRegistrationDto;
	}
}
