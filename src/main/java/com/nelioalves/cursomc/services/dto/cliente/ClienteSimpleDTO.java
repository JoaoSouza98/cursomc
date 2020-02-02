package com.nelioalves.cursomc.services.dto.cliente;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.enums.TipoCliente;

public final class ClienteSimpleDTO {
	
	public final Integer id;
	public final String nome;
	public final String email;
	public final String cpfOuCnpj;
	public final String tipo;
	
	public ClienteSimpleDTO (Integer id, String nome, String email, String cpfOuCnpj, String tipo) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo;
	}
	
	public static ClienteSimpleDTO from (Cliente obj) {
		return new ClienteSimpleDTO (
			obj.id,
			obj.nome,
			obj.email,
			obj.cpfOuCnpj,
			TipoCliente.toEnum(obj.tipo).toString()
		);
	}
	
	public static List<ClienteSimpleDTO> fromList(List<Cliente> list) {
		return list.stream().map(obj -> ClienteSimpleDTO.from(obj)).collect(Collectors.toList());
	}
	
	public static Page<ClienteSimpleDTO> fromPage(Page<Cliente> page) {
		return  page.map(obj -> ClienteSimpleDTO.from(obj));
	}
	
	public static Cliente to(ClienteSimpleDTO dto) {
		return new Cliente(dto.id, dto.nome, dto.email, null, null);
	}
}