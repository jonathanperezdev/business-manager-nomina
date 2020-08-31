package com.business.manager.nomina.service.exceptions.errors;

public enum ErrorEnum {
	//Parametros
	PARAMETRO_NOT_FOUND("No existe el parametro %s"),

	//Periodos Pago
	PERIODOS_PAGO_NOT_FOUND("No existen periodos de pago creados"),
	PERIODO_PAGO_IS_NOT_PENDING("El periodo de pago id: %s no esta en estado pendiente")
	;
	
	private String message;
	
	private ErrorEnum(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

}
