package ru.vsu.cs.sale_flats.exception.handler;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.vsu.cs.sale_flats.exception.RegistrationException;

@ControllerAdvice
@NoArgsConstructor
public class MainExceptionHandler {

	@ExceptionHandler(RegistrationException.class)
	public ModelAndView handleUserExistException(Exception ex) {
		RegistrationException exception = (RegistrationException) ex;

		ModelAndView mav = new ModelAndView();
		mav.addObject("message", ex.getMessage());
		mav.addObject("usr", exception.personRegistrationDto);
		mav.setViewName("registration");

		return mav;
	}

}
