package com.nelioalves.cursomc.services.validation.utils;

import javax.validation.Validation;

import org.hibernate.validator.constraints.br.CPF;

public class Cpf implements Valid {
	
	@CPF
	private String valor;
	
	public Cpf() {
	}
	
	public Cpf(String valor) {
		this.valor = valor;
	}

	@Override
	public boolean isValid(Object value) {
		return Validation.buildDefaultValidatorFactory()
				.getValidator()
				.validate(new Cpf((String) value))
				.isEmpty();
	}
}
