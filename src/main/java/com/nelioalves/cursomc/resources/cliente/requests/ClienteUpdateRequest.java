package com.nelioalves.cursomc.resources.cliente.requests;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.nelioalves.cursomc.services.dto.cliente.ClienteSimpleDTO;

public final class ClienteUpdateRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Preenchimento obrigatorio")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	public final String nome = null;
	
	@NotBlank(message = "Preenchimento obrigatorio")
	@Email(message = "Email invalido")
	public final String email = null;
	
	public static ClienteSimpleDTO to(ClienteUpdateRequest request) {
		return new ClienteSimpleDTO(null, request.nome, request.email, null, null);
	}
	
	public static ClienteSimpleDTO to(ClienteUpdateRequest request, Integer id) {
		return new ClienteSimpleDTO(id, request.nome, request.email, null, null);
	}
}
