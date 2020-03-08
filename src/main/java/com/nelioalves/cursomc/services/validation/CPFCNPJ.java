package com.nelioalves.cursomc.services.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.nelioalves.cursomc.services.validation.cliente.validators.ClienteCPFCNPJValidator;

@Constraint(validatedBy = ClienteCPFCNPJValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CPFCNPJ {
	String message() default "Documento inválido ou incompatível com o tipo de cliente informado.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}