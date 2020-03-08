package com.nelioalves.cursomc.services.validation.cliente.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nelioalves.cursomc.resources.cliente.requests.ClienteNewRequest;
import com.nelioalves.cursomc.services.validation.CPFCNPJ;
import com.nelioalves.cursomc.services.validation.utils.Valid;

public class ClienteCPFCNPJValidator implements ConstraintValidator<CPFCNPJ, ClienteNewRequest> {
	
	@Override
	public void initialize(CPFCNPJ ann) {
	}

	@Override
	public boolean isValid(ClienteNewRequest value, ConstraintValidatorContext context) {
		
		Valid validImpl = value.tipo.getDocumentValidator();
		
		Boolean isValid = validImpl.isValid(value.cpfOuCnpj); 
		
		if(!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(formatErrMsg(validImpl))
			.addConstraintViolation();
		}
		
		return isValid;
	}
	
	private String formatErrMsg(Object objeto) {
		return objeto.getClass().getSimpleName().toUpperCase().concat(" inválido");
	}
}