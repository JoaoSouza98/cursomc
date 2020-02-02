package com.nelioalves.cursomc.services.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.nelioalves.cursomc.domain.Endereco;

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
}
