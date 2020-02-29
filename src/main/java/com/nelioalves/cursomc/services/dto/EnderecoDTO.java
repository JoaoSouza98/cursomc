package com.nelioalves.cursomc.services.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.Estado;

public class EnderecoDTO {
	
	public final String logradouro;
	public final String numero;
	public final String complemento;
	public final String bairro;
	public final String cep;
	public final String cidade;
	public final String estado;
	
	public EnderecoDTO(String logradouro, String numero, String complemento, String bairro, String cep, String cidade, String estado) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
	}
	
	public static EnderecoDTO from(Endereco obj) {
		return new EnderecoDTO (
			obj.logradouro,
			obj.numero,
			obj.complemento,
			obj.bairro,
			obj.cep,
			obj.cidade.nome,
			obj.cidade.estado.nome
		);
	}
	
	public static List<EnderecoDTO> fromList(List<Endereco> list) {
		return list.stream().map(obj -> EnderecoDTO.from(obj)).collect(Collectors.toList());
	}
	
	public static Endereco to(EnderecoDTO dto, Integer clienteId) {
		return new Endereco(null, dto.logradouro, dto.numero, dto.complemento, dto.bairro, dto.cep, new Cliente(clienteId, null, null, null, null, null), new Cidade(null, dto.cidade, new Estado(null, dto.estado)));
	}
	
	public static List<Endereco> toList(List<EnderecoDTO> list, Integer clienteId) {
		return list.stream().map(e -> EnderecoDTO.to(e, clienteId)).collect(Collectors.toList());
	}
}