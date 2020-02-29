package com.nelioalves.cursomc.resources.cliente.requests;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

import com.nelioalves.cursomc.services.dto.EnderecoDTO;
import com.nelioalves.cursomc.services.dto.cliente.ClienteDetailDTO;

public class ClienteNewRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public final String nome;
	public final String email;
	public final String cpfOuCnpj;
	public final String tipo;
	
	public final String logradouro;
	public final String numero;
	public final String complemento;
	public final String bairro;
	public final String cep;
	
	public final Integer cidadeId;
	
	public final Set<String> telefones;

	public ClienteNewRequest(String nome, String email, String cpfOuCnpj, String tipo, String logradouro,
			String numero, String complemento, String bairro, String cep, Set<String> telefones, Integer cidadeId) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.telefones = telefones;
		this.cidadeId = cidadeId;
	}
	
	public static ClienteDetailDTO to (ClienteNewRequest obj) {
		return new ClienteDetailDTO(null, obj.nome, obj.email, obj.cpfOuCnpj, obj.tipo, 
				Arrays.asList(new EnderecoDTO(obj.logradouro, obj.numero, obj.complemento, obj.bairro, obj.cep, null, null)), obj.telefones);
	}
}