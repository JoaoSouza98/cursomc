package com.nelioalves.cursomc.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.nelioalves.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public CategoriaDTO() {
	}
	
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public static List<CategoriaDTO> fromList(List<Categoria> list) {
		return list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
	}
	
	public static Page<CategoriaDTO> fromPage(Page<Categoria> page) {
		return  page.map(obj -> new CategoriaDTO(obj));
	}
}
