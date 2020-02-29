package com.nelioalves.cursomc.services.dto;

import com.nelioalves.cursomc.domain.Cidade;

public class CidadeDTO {
	public final Integer id;
	public final String nome;
	
	public CidadeDTO(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public static CidadeDTO from (Cidade obj) {
		return new CidadeDTO(obj.id, obj.nome);
	}
}