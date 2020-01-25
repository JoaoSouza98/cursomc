package com.nelioalves.cursomc.services.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import com.nelioalves.cursomc.domain.Categoria;

public final class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public final Integer id;
	
	@NotBlank(message="Preenchimento obrigatorio")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	public final String nome;
	
	public CategoriaDTO(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public static CategoriaDTO from(Categoria obj) {
		return new CategoriaDTO(
				obj.id,
				obj.nome
		);
	}
	
	public static List<CategoriaDTO> fromList(List<Categoria> list) {
		return list.stream().map(obj -> CategoriaDTO.from(obj)).collect(Collectors.toList());
	}
	
	public static Page<CategoriaDTO> fromPage(Page<Categoria> page) {
		return  page.map(obj -> CategoriaDTO.from(obj));
	}
	
	public static Categoria to(CategoriaDTO dto) {
		return new Categoria(dto.id, dto.nome);
	}
}
