package com.business.manager.nomina.service.exceptions;

import com.business.manager.nomina.service.exceptions.errors.ErrorEnum;

public class NoDataFoundException extends BaseException {
	
	public NoDataFoundException(ErrorEnum error, Object... args) {
		super(error, args);
	}

}
