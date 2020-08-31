package com.business.manager.nomina.service.exceptions.handlers;

import com.business.manager.nomina.service.exceptions.NoDataFoundException;
import com.business.manager.nomina.service.exceptions.errors.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NominaExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(NominaExceptionHandler.class);

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoDataFoundException.class)
	@ResponseBody
	public ErrorResponse noDataFoundException(NoDataFoundException ex) {
		LOGGER.info("Handling exception class={}", ex.getClass());

		return ErrorResponse.newErrorResponse(ex);
	}
}
