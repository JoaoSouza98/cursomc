package com.nelioalves.cursomc.services.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import com.nelioalves.cursomc.domain.Cliente;

public final class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Integer id;
	
	@NotBlank(message="Preenchimento obrigatorio")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 80 caracteres")
	public final String nome;
	
	@NotBlank(message="Preenchimento obrigatorio")
	@Email(message="Email invalido")
	public final String email;
	
	public ClienteDTO(Cliente obj) {
		id = obj.id;
		nome = obj.nome;
		email = obj.nome;
	}
	
	public static List<ClienteDTO> fromList(List<Cliente> list) {
		return list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
	}
	
	public static Page<ClienteDTO> fromPage(Page<Cliente> page) {
		return  page.map(obj -> new ClienteDTO(obj));
	}
	
	public static Cliente to(ClienteDTO dto) {
		return new Cliente(dto.id, dto.nome, dto.email, null, null);
	}
}
