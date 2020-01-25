package com.nelioalves.cursomc.resources.requests;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.nelioalves.cursomc.services.dto.CategoriaDTO;

public class CategoriaInsertRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message="Preenchimento obrigatorio")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	public final String nome = null;
	
	public static CategoriaDTO to(CategoriaInsertRequest obj) {
		return new CategoriaDTO (
				null,
				obj.nome
		);
	}
}
