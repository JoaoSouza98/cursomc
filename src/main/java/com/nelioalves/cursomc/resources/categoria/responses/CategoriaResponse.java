package com.nelioalves.cursomc.resources.categoria.responses;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.nelioalves.cursomc.services.dto.CategoriaDTO;

public class CategoriaResponse {
	
	public final Integer id;
	public final String nome;
	
	public CategoriaResponse(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public static CategoriaResponse from(CategoriaDTO obj) {
		return new CategoriaResponse(obj.id, obj.nome);
	}
	
	public static List<CategoriaResponse> fromList(List<CategoriaDTO> list) {
		return list.stream().map(obj -> CategoriaResponse.from(obj)).collect(Collectors.toList());
	}
	
	public static Page<CategoriaResponse> fromPage(Page<CategoriaDTO> page) {
		return page.map(obj -> CategoriaResponse.from(obj));
	}
}