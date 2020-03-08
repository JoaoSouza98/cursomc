package com.nelioalves.cursomc.services.validation.utils;

import javax.validation.Validation;

import org.hibernate.validator.constraints.br.CNPJ;

public class Cnpj implements Valid {
	
	@CNPJ
	private String valor;
	
	public Cnpj() {
	}
	
	public Cnpj(String valor) {
		this.valor = valor;
	}

	@Override
	public boolean isValid(Object value) {
		return Validation.buildDefaultValidatorFactory()
				.getValidator()
				.validate(new Cnpj((String) value))
				.isEmpty();
	}
}
