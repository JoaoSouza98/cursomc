package com.nelioalves.cursomc.services.dto.cliente;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.services.dto.EnderecoDTO;

public final class ClienteDetailDTO {
	
	public final Integer id;
	public final String nome;
	public final String email;
	public final String cpfOuCnpj;
	public final String tipo;
	public final List<EnderecoDTO> enderecos;
	public final Set<String> telefones;
	
	
	public ClienteDetailDTO (Integer id, String nome, String email, String cpfOuCnpj, 
			String tipo, List<EnderecoDTO> enderecos, Set<String> telefones) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo;
		this.enderecos = enderecos;
		this.telefones = telefones;
	}
	
	public static ClienteDetailDTO from (Cliente obj) {
		return new ClienteDetailDTO (
			obj.id,
			obj.nome,
			obj.email,
			obj.cpfOuCnpj,
			TipoCliente.toEnum(obj.tipo).toString(),
			EnderecoDTO.fromList(obj.enderecos),
			obj.telefones
		);
	}
	
	public static List<ClienteDetailDTO> fromList(List<Cliente> list) {
		return list.stream().map(obj -> ClienteDetailDTO.from(obj)).collect(Collectors.toList());
	}
	
	public static Page<ClienteDetailDTO> fromPage(Page<Cliente> page) {
		return  page.map(obj -> ClienteDetailDTO.from(obj));
	}
	
	public static Cliente to(ClienteDetailDTO dto) {
		return new Cliente(dto.id, dto.nome, dto.email, null, null, EnderecoDTO.toList(dto.enderecos, dto.id));
	}
}
