package com.business.manager.nomina.service.exceptions;

import com.business.manager.nomina.service.exceptions.errors.ErrorEnum;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private ErrorEnum error;
	private String message;
    
    public BaseException(ErrorEnum error, Object... args) {
        super(String.format(error.getMessage(), args));
        this.message = String.format(error.getMessage(), args);
        this.error = error;
    }
}
