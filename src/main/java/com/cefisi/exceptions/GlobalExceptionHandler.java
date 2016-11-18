package com.cefisi.exceptions;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.PersistenceException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author plasse
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(PersistenceException.class)
	public ModelAndView handleError() {
		ModelAndView mav = new ModelAndView("persistenceSqlException");
		mav.addObject("date", new SimpleDateFormat("d/M/Y k:m:s").format(new Date()));
		return mav;
	}

}
